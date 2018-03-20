from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS

app_object = Flask(__name__)
CORS(app_object)
app_object.config.from_object('config')
db = SQLAlchemy(app_object)
from .models import model_user
db.create_all()
