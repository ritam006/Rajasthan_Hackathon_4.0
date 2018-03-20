from flask import request, jsonify, Response, current_app
from ..models import model_user

def add_new_common():
    if request.method == 'POST':
        kwags = request.get_json(force=True)
        username = kwags.get('username')
        password = kwags.get('password')
        name = kwags.get('name')
        district = kwags.get('district')
        address = kwags.get('address')
        response = model_user.Auth.add_new_common(username, password, name, district, address)
        if response is None:
            return Response(status=400)
        return jsonify(response)

def add_new_official():
    if request.method == 'POST':
        kwags = request.get_json(force=True)
        username = kwags.get('username')
        password = kwags.get('password')
        name = kwags.get('name')
        response = model_user.Auth.add_new_official(username, password, name)
        if response is None:
            return Response(status=400)
        return jsonify(response)

def login():
    if request.method == 'PUT':
        kwags = request.get_json(force=True)
        username = kwags.get('username')
        password = kwags.get('password')
        response = model_user.Auth.login(username, password)
        if response is None:
            return Response(status=400)
        return jsonify(response)
