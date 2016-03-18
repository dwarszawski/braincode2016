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