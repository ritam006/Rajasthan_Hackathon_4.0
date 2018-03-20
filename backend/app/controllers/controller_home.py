from flask import request, jsonify, Response, current_app
from ..models import model_user, model_home

def get_households():
    if request.method == 'PUT':
        kwags = request.get_json(force=True)
        district = kwags.get('district')
        response = model_home.get_households(district)
        if response is None:
            return Response(status=400)
        return jsonify(response)

def get_locations():
    if request.method == 'PUT':
        kwags = request.get_json(force=True)
        district = kwags.get('district')
        response = model_home.get_locations(district)
        if response is None:
            return Response(status=400)
        return jsonify(response)



