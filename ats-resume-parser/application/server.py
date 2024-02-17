import os
import secrets
from flask import Flask, flash, request, redirect, make_response
from py_eureka_client import eureka_client
from werkzeug.utils import secure_filename
from env_parser import parse_env_file
from resume_parser import ResumeParser

UPLOAD_FOLDER = '../uploads/'
ALLOWED_EXTENSIONS = {'pdf'}

env_variables = parse_env_file()

eureka_client.init(eureka_server=os.getenv("ATS_DISCOVERY_SERVICE_URL", "http://localhost:8761/eureka"),
                   app_name="ats-resume-parser",
                   instance_port=int(os.getenv("RESUME_PARSER_PORT", '8050')),
                   instance_host="127.0.0.1")

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['MAX_CONTENT_LENGTH'] = 16 * 1000 * 1000
app.add_url_rule("/resume/<name>", endpoint="resume", build_only=True)
app.secret_key = secrets.token_urlsafe(32)

parser = ResumeParser(os.getenv('OPENAI_API_KEY', env_variables.get('OPENAI_API_KEY')))


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app.route("/resume/", methods=['POST'])
def upload_resume():
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            flash('No file part')
            return redirect(request.url)
        file = request.files['file']
        # If the user does not select a file, the browser submits an
        # empty file without a filename.
        if file.filename == '':
            flash('No selected file')
            return redirect(request.url)
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            return make_response("file has been uploaded", 200)


@app.route('/resume/<name>/')
def display_resume(name):
    resume_path = os.path.join(app.config["UPLOAD_FOLDER"], name)
    return parser.query_resume(resume_path)


if __name__ == "__main__":
    host = os.getenv("RESUME_PARSER_HOST", '0.0.0.0')
    port = os.getenv("RESUME_PARSER_PORT", '5000')
    assert port.isnumeric(), 'port must be an integer'
    port = int(port)
    app.run(host=host, port=port, debug=True)
