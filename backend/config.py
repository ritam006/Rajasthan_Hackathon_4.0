import os
DEBUG = True
SECRET_KEY = os.urandom(24)
PORT = 5000

SQLALCHEMY_DATABASE_URI = 'mysql+pymysql://root:password@localhost/rajasthan_project'

NO_OF_PROJECTS = 10
