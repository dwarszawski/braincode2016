# braincode2016
Brain Code 2016 - hackaton


GET na:
https://api.natelefon.pl/v1/allegro/categories?parentCategory=4029&access_token=7c9b0c140491b6b0ef5d6b70dadd367c2d2cb2c194b8c94bfd291767edaf8d1b - kategoria motoryzacja - samochody - osobowe


POST na:
https://api.natelefon.pl/v2/allegro/offers?access_token=7c9b0c140491b6b0ef5d6b70dadd367c2d2cb2c194b8c94bfd291767edaf8d1b
{
"category": 4029,
"access_token": "7c9b0c140491b6b0ef5d6b70dadd367c2d2cb2c194b8c94bfd291767edaf8d1b"
}

zwraca piersze 50 samochodow

GET na:
https://api.natelefon.pl/v2/allegro/offers/6032893654?access_token=7c9b0c140491b6b0ef5d6b70dadd367c2d2cb2c194b8c94bfd291767edaf8d1b zwraca przykladowa aukcje, audi A6


Kategorie z allegro
{"categories":[{"hasChildren":true,"id":"57967","name":"Acura"},{"hasChildren":true,"id":"76463","name":"Aixam"},{"hasChildren":true,"id":"4030","name":"Alfa Romeo"},{"hasChildren":true,"id":"76619","name":"Aro"},{"hasChildren":true,"id":"18091","name":"Aston Martin"},{"hasChildren":true,"id":"4031","name":"Audi"},{"hasChildren":true,"id":"57997","name":"Bentley"},{"hasChildren":true,"id":"4032","name":"BMW"},{"hasChildren":true,"id":"76630","name":"Bugatti"},{"hasChildren":false,"id":"18092","name":"Buick"},{"hasChildren":true,"id":"18093","name":"Cadillac"},{"hasChildren":false,"id":"249882","name":"Chatenet"},{"hasChildren":true,"id":"12399","name":"Chevrolet"},{"hasChildren":true,"id":"12400","name":"Chrysler"},{"hasChildren":true,"id":"4033","name":"Citroen"},{"hasChildren":true,"id":"18118","name":"Dacia"},{"hasChildren":true,"id":"4034","name":"Daewoo"},{"hasChildren":true,"id":"12401","name":"Daihatsu"},{"hasChildren":true,"id":"12402","name":"Dodge"},{"hasChildren":true,"id":"18134","name":"Ferrari"},{"hasChildren":true,"id":"4035","name":"Fiat"},{"hasChildren":true,"id":"4036","name":"Ford"},{"hasChildren":true,"id":"4037","name":"Honda"},{"hasChildren":true,"id":"18193","name":"Hummer"},{"hasChildren":true,"id":"4038","name":"Hyundai"},{"hasChildren":true,"id":"18197","name":"Infiniti"},{"hasChildren":true,"id":"12403","name":"Isuzu"},{"hasChildren":true,"id":"8641","name":"Jaguar"},{"hasChildren":true,"id":"12404","name":"Jeep"},{"hasChildren":true,"id":"8642","name":"Kia"},{"hasChildren":true,"id":"57982","name":"Lamborghini"},{"hasChildren":true,"id":"4039","name":"Lancia"},{"hasChildren":true,"id":"12405","name":"Land Rover"},{"hasChildren":true,"id":"12406","name":"Lexus"},{"hasChildren":false,"id":"249883","name":"Ligier"},{"hasChildren":true,"id":"18214","name":"Lincoln"},{"hasChildren":true,"id":"8643","name":"Łada"},{"hasChildren":true,"id":"18224","name":"Maserati"},{"hasChildren":true,"id":"4040","name":"Mazda"},{"hasChildren":true,"id":"4041","name":"Mercedes-Benz"},{"hasChildren":false,"id":"57993","name":"Mercury"},{"hasChildren":true,"id":"57999","name":"MG"},{"hasChildren":false,"id":"249884","name":"Microcar"},{"hasChildren":true,"id":"18281","name":"Mini"},{"hasChildren":true,"id":"4042","name":"Mitsubishi"},{"hasChildren":true,"id":"4043","name":"Nissan"},{"hasChildren":false,"id":"18331","name":"Oldsmobile"},{"hasChildren":true,"id":"4044","name":"Opel"},{"hasChildren":true,"id":"4045","name":"Peugeot"},{"hasChildren":false,"id":"12407","name":"Plymouth"},{"hasChildren":true,"id":"4046","name":"Polonez"},{"hasChildren":true,"id":"12408","name":"Pontiac"},{"hasChildren":true,"id":"8644","name":"Porsche"},{"hasChildren":false,"id":"58000","name":"Proton"},{"hasChildren":true,"id":"4047","name":"Renault"},{"hasChildren":false,"id":"58001","name":"Rolls-Royce"},{"hasChildren":true,"id":"4048","name":"Rover"},{"hasChildren":true,"id":"4049","name":"Saab"},{"hasChildren":true,"id":"4050","name":"Seat"},{"hasChildren":true,"id":"4051","name":"Skoda"},{"hasChildren":true,"id":"12409","name":"Smart"},{"hasChildren":true,"id":"18396","name":"SsangYong"},{"hasChildren":true,"id":"4052","name":"Subaru"},{"hasChildren":true,"id":"4053","name":"Suzuki"},{"hasChildren":true,"id":"58006","name":"Tata"},{"hasChildren":false,"id":"18407","name":"Tavria"},{"hasChildren":false,"id":"250762","name":"Tesla"},{"hasChildren":true,"id":"4054","name":"Toyota"},{"hasChildren":false,"id":"4057","name":"Trabant"},{"hasChildren":false,"id":"12410","name":"UAZ"},{"hasChildren":true,"id":"4055","name":"Volkswagen"},{"hasChildren":true,"id":"4056","name":"Volvo"},{"hasChildren":false,"id":"8645","name":"Wartburg"},{"hasChildren":false,"id":"4058","name":"Pozostałe"}]}

Mozna recznie triggerowac ladowanie samochodow spod danej kategorii wysylajac get na:
localhost:8080/{categoryId}
np: 

localhost:8080/4031 sciagnie Audi
