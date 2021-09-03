import requests
BASE = "http://localhost:8080/character/"

def post( name ):
	response = requests.post( BASE + "id/" + name )

def main():
	with open ( "character_names.txt" , "r" ,encoding="utf8" ) as F :
		for name in F:
			post( "_".join(name.split()) )

if __name__ == '__main__':
	main()