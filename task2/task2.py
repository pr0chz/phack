#!/usr/bin/python3
import pandas as pd
from sklearn.linear_model import LogisticRegression
import json
import sys

def compute_func(input):
    m = pd.DataFrame(input['measurements'])
    s = pd.DataFrame(input['samples'])

    fields = ['brake-distance', 'noise-level', 'vibrations']

    model = LogisticRegression()
    model.fit(m[fields], m['type'])

    s['type'] = model.predict(s[fields])

    result = s[['id', 'type']].to_dict(orient="records")
    result = json.dumps({"result": result})
    return result

if __name__ == '__main__':
    # service.py executed as script
    # do something
    if len(sys.argv) > 1:
      args = sys.argv[1]
    else:
      args = json.loads(sys.stdin.read())

    print(compute_func(args))

