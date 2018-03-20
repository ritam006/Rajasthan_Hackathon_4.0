from app import db
from sqlalchemy.exc import IntegrityError
from flask import Response
from passlib.apps import custom_app_context as pwd_context
from werkzeug.security import generate_password_hash, check_password_hash

class Common(db.Model):
    __tablename__ = 'common'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(32), nullable=False)
    address = db.Column(db.String(128), nullable=False)
    district = db.Column(db.String(32), nullable=False)
    phone_number = db.Column(db.String(20), unique=True, nullable=False)

class Official(db.Model):
    __tablename__ = 'official'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(32), nullable=False)
    office_id = db.Column(db.String(32), unique=True, nullable=False)

class Auth(db.Model):
    __tablename__ = 'auth'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(32), unique=True, nullable=False)
    password = db.Column(db.String(128), nullable=False)
    usertype = db.Column(db.Integer)

    @staticmethod
    def add_new_common(username, password, name, district, address):
        if username is None or password is None:
            return None # missing arguments
        try:
            auth = Auth(username=username, password=generate_password_hash(password), usertype=1)
            db.session.add(auth)
            db.session.commit()
        except IntegrityError:
            db.session.rollback()
            return None
        try:
            common_user = Common(name=name, address=address, district=district, phone_number=username)
            db.session.add(common_user)
            db.session.commit()
        except IntegrityError:
            db.session.rollback()
            return None

        res = {}
        res['name'] = common_user.name
        res['district'] = common_user.district
        res['address'] = common_user.address
        res['phone_number'] = common_user.phone_number
        res['id'] = common_user.id
        res['usertype'] = 1
        return res

    @staticmethod
    def add_new_official(username, password, name):
        if username is None or password is None:
            return None # missing arguments
        try:
            auth = Auth(username=username, password=generate_password_hash(password), usertype=2)
            db.session.add(auth)
            db.session.commit()
        except IntegrityError:
            db.session.rollback()
            return None

        try:
            official = Official(name=name, office_id=username)
            db.session.add(official)
            db.session.commit()
        except IntegrityError:
            db.session.rollback()
            return None

        res = {}
        res['name'] = official.name
        res['office_id'] = official.office_id
        res['id'] = official.id
        res['usertype'] = 2
        return res

    @staticmethod
    def login(username, password):
        if username is None or password is None:
            return None # missing arguments
        auth = Auth.query.filter_by(username=username).first()
        if auth is None:
            return None
        if check_password_hash(auth.password, password) is False:
            return None
        res = {}
        res['usertype'] = auth.usertype
        if auth.usertype==2:
            official = Official.query.filter_by(office_id=username).first()
            res['office_id'] = official.office_id
            res['name'] = official.name
            res['districts'] = [
                'Ajmer',
                'Alwar',
                'Bikaner',
                'Jaipur',
                'Jaisalmer'
            ]
        return res
