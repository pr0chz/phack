#!/usr/bin/python3
import pandas as pd
from sklearn.linear_model import LogisticRegression
import json
import sys

input = json.loads(sys.stdin.read())

m = pd.DataFrame(input['measurements'])
s = pd.DataFrame(input['samples'])

fields = ['brake-distance', 'noise-level', 'vibrations']

model = LogisticRegression()
model.fit(m[fields], m['type'])

s['type'] = model.predict(s[fields])

result = s[['id', 'type']].to_dict(orient="records")
result = json.dumps({"result": result})

print(result)
