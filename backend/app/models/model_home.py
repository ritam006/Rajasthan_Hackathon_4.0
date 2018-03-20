from app import db
from sqlalchemy.exc import IntegrityError
from flask import Response
import numpy as np

def get_locations(district):
    res={
        "locations":[
            {
                "lat":29.986852,
                "long":74.745208
            },
            {
                "lat":26.924608,
                "long":70.915321
            }
        ]
    }
    return res

def get_households(district):

    weights=[[10.17807103, 12.35468548,  8.0063826,   4.65966573,  6.259062],
    [10.52441416,  2.70651346,  7.02478256,  5.46975906,  6.54965287]]

    data=[[0, 0, 0, 0, 0, 0, 1, 1, 1, 1],
        [0, 0, 0, 0, 0, 0, 1, 0, 0, 1],
        [0, 2, 1, 2, 1, 1, 2, 2, 0, 3],
        [2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
        [0, 2, 0, 1, 1, 0, 1, 0, 0, 0]]

    final=np.dot(weights, data)
    final_trans=np.round(final.T)
    res={ "households":[ {
        "name":"Sagarpreet Chadha",
        "phone":"8978657484",
        "water_clean":final_trans[0][0],
        "water_normal":final_trans[0][1],
        "address":"Jawaharlal Nehru Marg, Jaipur"
    },
    {
            "name":"Somesh Tiwari",
            "phone":"9978657484",
            "water_clean":final_trans[1][0],
            "water_normal":final_trans[1][1],
            "address":"K M Munshi Marg, Opposite OTS"
        },
    {
            "name":"Vaibhav Kumar",
            "phone":"8878657484",
            "water_clean":final_trans[2][0],
            "water_normal":final_trans[2][1],
            "address":"Bhawani Singh Marg, Rambagh Crossing"
        },
    {
            "name":"Ritam Singh",
            "phone":"7978657484",
            "water_clean":final_trans[3][0],
            "water_normal":final_trans[3][1],
            "address":"Behind new assembly, Lal Kothi Scheme"
        },
    {
            "name":"Harsh Agarwal",
            "phone":"8778657484",
            "water_clean":final_trans[4][0],
            "water_normal":final_trans[4][1],
            "address":"Opposite VT Road, Shipra Path, Mansarovar"
        },
    {
            "name":"Rahul Jain",
            "phone":"7778657484",
            "water_clean":final_trans[5][0],
            "water_normal":final_trans[5][1],
            "address":"Rajendra Chowk, Cantt Area, Military Station"
        },
    {
            "name":"Parteek Rawal",
            "phone":"7878657484",
            "water_clean":final_trans[6][0],
            "water_normal":final_trans[6][1],
            "address":"Sector-17, Pratap Nagar, Sanganer"
        },
    {
            "name":"Zakir Khan",
            "phone":"8778657484",
            "water_clean":final_trans[7][0],
            "water_normal":final_trans[7][1],
            "address":"St. Edmund's School, Malviya Nagar"
        },
    {
            "name":"Archit Goyal",
            "phone":"8667657484",
            "water_clean":final_trans[8][0],
            "water_normal":final_trans[8][1],
            "address":"Ajmeri Gate, Sawai Ram Singh Rd, Panch Batti, Sangram Colony"
        },
    {
            "name":"Mudit Verma",
            "phone":"9999657484",
            "water_clean":final_trans[9][0],
            "water_normal":final_trans[9][1],
            "address":"2, Mahaveer Nagar, Shri Vihar, Maharani Farm, Mahaveer Nagar"
        }

    ] }

    return res
