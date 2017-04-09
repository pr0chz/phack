#!/usr/bin/python3

# Inspired by snippet written by Nathan Hamiel (2010)
import json
import os
from http.server import HTTPServer, BaseHTTPRequestHandler
from optparse import OptionParser
from task2 import compute_func

class RequestHandler(BaseHTTPRequestHandler):
         
    def do_POST(self):
        result = compute_func(json.loads(self.rfile.read(int(self.headers.get('content-length'))).decode('utf-8')))
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(result).encode('utf-8'))
        return   

def main():
    port = int(os.environ["PORT"])
    print('Listening on localhost:%s' % port)
    server = HTTPServer(('', port), RequestHandler)
    server.serve_forever()

        
if __name__ == "__main__":
    parser = OptionParser()
    parser.usage = ("Creates an http-server that will echo out any GET or POST parameters\n"
                    "Run:\n\n"
                    "   reflect")
    (options, args) = parser.parse_args()
    
    main()