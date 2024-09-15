from flask import Flask, request
import os

app = Flask(__name__)


@app.route('/')
def hello_world():
    ip_addr = request.remote_addr
    return '<h1> You are connecting from: ' + ip_addr


if __name__ == '__main__':
    port = int(os.environ.get('PORT', 8080))
    app.run(debug=True, host='0.0.0.0', port=port)
