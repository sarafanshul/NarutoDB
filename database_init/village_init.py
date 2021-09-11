import requests
BASE = "http://localhost:8080/village/"

def post( name ):
	return requests.post( BASE + "id/" + name )

def get( name ):
	return requests.get( BASE + "id/" + name )

def main():
	with open ( "village_list.txt" , "r" ,encoding="utf8" ) as F :
		for name in F:
			r = post( "_".join(name.split()) )
			if( r.status_code == 404 ):
				print("_".join(name.split()))


def test():
	with open ( "village_list.txt" , "r" ,encoding="utf8" ) as F :
		with open("not_found.txt" , "w" ,encoding="utf8" ) as O:
			for name in F:
				r = get( "_".join(name.split()) )
				if( r.status_code == 404 ):
					print( name + " Not found !" )
					O.write("_".join(name.split()) + "\n")


if __name__ == '__main__':
	main()
# 	test()