#pip install bottle  
#Option1: import only the required method
#from bottle import route, run, template, debug

#Option2: import all methods
from bottle import *

#Option3: import the whole library
#import bottle


@route('/hello/<name>')
def index(name):
    return template('<b>Hai {{name}}</b>!', name=name)

#@bottle.route('/')
@route('/')
def home_page():
	return "Hello, Welcome to Home Page, Murugan Nagarajan\n"
	
@route('/testpage')
def test_page():
	return "This is a Test Page for testing the connectivity"

#@bottle.debug(True)	
debug(True)
run(host='localhost', port=8080)
