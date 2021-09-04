import requests
BASE = "http://localhost:8080/character/"

def post( name ):
	response = requests.post( BASE + "id/" + name )

def get( name ):
	return requests.get( BASE + "id/" + name )

def main():
	with open ( "character_names.txt" , "r" ,encoding="utf8" ) as F :
		for name in F:
			post( "_".join(name.split()) )


def test():
	with open ( "character_names.txt" , "r" ,encoding="utf8" ) as F :
		with open("not_found.txt" , "w" ,encoding="utf8" ) as O:
			for name in F:
				r = get( "_".join(name.split()) )
				if( r.status_code == 404 ):
					print( name + " Not found !" )
					O.write("_".join(name.split()) + "\n")


if __name__ == '__main__':
	# main()
	# test()