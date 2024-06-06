
/* Esercitazione su database Cielo 2 

Volo (_codice_, _comp_, durataMinuti) 
foreign key: comp references Compagnia(nome) [VincoloDB.2] 
foreign key: (codice, comp) references ArrPart(codice, comp)

ArrPart (_codice_, _comp_, arrivo, partenza)
foreign key: (codice, comp) references Volo(codice, comp) 
foreign key: arrivo references Aeroporto(codice)
foreign key: partenza references Peroporto(codice)

Aeroporto (_codice_, nome)
foreign key: codice references LuogoAeroporto(aeroporto)
 
LuogoAeroporto (_aeroporto_, citta, nazione) 
foreign key: aeroporto references Aeroporto(codice)

Compagnia (_nome_, annoFondaz)   annoFondaz può essere NULL
*/

/*
   1. Quante sono le compagnie che operano 
   (sia in arrivo che in partenza) nei diversi aeroporti?
*/

select a.codice, 
       a.nome, 
       count(distinct c.nome) as num_comp

from  arrpart as ap,
      aeroporto as a, 
      volo as v,
      compagnia as c

where v.comp = c.nome and
      ap.codice = v.codice and
	  ap.comp = v.comp and
	  (ap.arrivo = a.codice or ap.partenza = a.codice)

group by (a.codice, a.nome)
	  
	  
/* risultato : 

"CDG"	"Charles de Gaulle, Aeroport de Paris"	2
"CIA"	"Aeroporto di Roma Ciampino"	        2
"FCO"	"Aeroporto di Roma Fiumicino"	        3
"HTR"	"Heathrow Airport, London"	            3
"JFK"	"JFK Airport"	                        3

*/


/*

2. Quanti sono i voli che partono dall’aeroporto
  ‘HTR’ e hanno una durata di almeno 100 minuti?

*/

select count(*) as numero_voli
from aeroporto as a,
     arrpart as ap, 
     volo as v
where a.codice = 'HTR' and
	  ap.partenza = a.codice and 
	  ap.codice = v.codice and
	  ap.comp = v.comp and 
	  v.durataminuti >= '100'

-- risultato : 1


/*
 3. Quanti sono gli aeroporti sui quali opera la compagnia ‘Apitalia’, 
 per ogni nazione nella quale opera?
*/

select la.nazione, 
       count(distinct a.codice) as num_aeroporti
from luogoaeroporto la, 
     aeroporto a, 
	 arrpart as ap, 
	 volo as v, 
	 compagnia as c
	 
where c.nome = 'Apitalia' and
      v.comp = c.nome and 
	  ap.codice = v.codice and
	  ap.comp = v.comp and
	  (ap.arrivo = a.codice or ap.partenza = a.codice) and
      la.aeroporto = a.codice 
group by(la.nazione)
	  
	  
/* risultato :

"Italy"	            2
"United Kingdom"	1
"USA"	            1

*/


/*
 4. Qual è la media, il massimo e il minimo della
 durata dei voli effettuati dalla compagnia ‘MagicFly’?
*/

  select avg(v.durataminuti) as media,
         max(v.durataminuti) as massimo,
	     min(v.durataminuti) as minimo
from compagnia as c, 
     volo as v
where c.nome = 'MagicFly' and
      v.comp = c.nome 

--  risultato : 420.0000000000000000	600	60


/*
 5. Qual è l’anno di fondazione della compagnia 
 più vecchia che opera in ognuno degli aeroporti?
*/

select a.codice as codice_aeroporto,
       a.nome as nome_aeroporto,
	   min(c.annofondaz)
from compagnia as c, 
     volo as v,
	 aeroporto as a,
	 arrpart as ap
where v.comp = c.nome and
      ap.codice = v.codice and
	  ap.comp = v.comp and
	  (ap.arrivo = a.codice or ap.partenza = a.codice)
group by (a.codice, a.nome)

/* risultato :
    "CIA"	"Aeroporto di Roma Ciampino"	        1900
	"HTR"	"Heathrow Airport, London"	            1900
	"CDG"	"Charles de Gaulle, Aeroport de Paris"	1954
	"FCO"	"Aeroporto di Roma Fiumicino"	        1900
	"JFK"	"JFK Airport"	                        1900
*/

/*
6. Quante sono le nazioni (diverse) raggiungibili da ogni
   nazione tramite uno o più voli?
*/
   

select la1.nazione, count(distinct la2.nazione) as numero_nazioni_raggiungibili 
from luogoaeroporto as la1,
     aeroporto as a1,
	 luogoaeroporto as la2,
	 aeroporto as a2,
	 arrpart as ap,
	 volo as v
where a1.codice = la1.aeroporto and
      ap.partenza = a1.codice and
	  ap.arrivo = a2.codice and
	  a2.codice = la2.aeroporto and
	  la1.nazione != la2.nazione 
group by(la1.nazione) -- nazioni distinte da cui partono voli verso altre nazioni 

/* risultato :
 
 "France"	        1
 "Italy"	        1
 "United Kingdom"	2
 "USA"	            1

*/ 

/*
7. Qual è la durata media dei voli che partono da ognuno degli aeroporti?

*/

select a.codice, 
       a.nome,
	   avg(v.durataminuti) as durata_media

from aeroporto as a, 
     arrpart as ap,
	 volo as v
	 
where ap.partenza = a.codice and
      ap.codice = v.codice 

group by(a.codice, a.nome)

/* risultato :
  
"CIA"	"Aeroporto di Roma Ciampino"	        407.0000000000000000
"HTR"	"Heathrow Airport, London"	            105.0000000000000000
"CDG"	"Charles de Gaulle, Aeroport de Paris"	60.0000000000000000
"FCO"	"Aeroporto di Roma Fiumicino"	        545.5000000000000000
"JFK"	"JFK Airport"	                        599.5000000000000000

*/

/*
 8. Qual è la durata complessiva dei voli operanti da 
    ognuna delle compagnie fondate a partire dal 1950?
*/


select c.nome, 
       c.annofondaz, 
	   sum(v.durataminuti) as durata_complessiva

from compagnia as c,
     volo as v

where c.annofondaz >= 1950 and
      v.comp = c.nome

group by (c.nome, c.annofondaz)

/*risultato : 

 "Caimanair"	1954	1043
 "MagicFly"  	1990	1260

*/

-- 9. Quali sono gli aeroporti nei quali operano esattamente due compagnie?


select a.codice, 
       a.nome
from  arrpart as ap,
      aeroporto as a, 
      volo as v,
      compagnia as c

where v.comp = c.nome and
      ap.codice = v.codice and
	  ap.comp = v.comp and
	  (ap.arrivo = a.codice or ap.partenza = a.codice)

group by (a.codice, a.nome)
having  count(distinct c.nome) = 2 

/*risultato : 

 "CDG"	"Charles de Gaulle, Aeroport de Paris"
 "CIA"	"Aeroporto di Roma Ciampino"
  
*/


-- 10. Quali sono le città con almeno due aeroporti?


select distinct l1.citta
from LuogoAeroporto la1, LuogoAeroporto la2
where la1.citta = la2.citta 
  and la1.aeroporto <> la2.aeroporto;

/*  La condizione l1.citta = l2.citta seleziona tutte le coppie di righe
    che hanno la stessa città, mentre la condizione l1.aeroporto <> l2.aeroporto
    assicura che le due righe abbiano aeroporti diversi.
*/
	


/* 11. Qual è il nome delle compagnie i cui voli hanno 
   una durata media maggiore di 6 ore?
*/

select c.nome

from compagnia as c, 
     volo as v

where v.comp = c.nome

group by (c.nome)

having avg(v.durataminuti) > 360
 
/* risultato : 
   "Apitalia"
   "MagicFly"     
 
 /*
    12. Qual è il nome delle compagnie i cui
    voli hanno tutti una durata maggiore di 100 minuti?     
*/

select c.nome

from compagnia as c, 
     volo as v

where v.comp = c.nome

group by c.nome

having min( v.durataminuti ) > 100

-- risultato : "Apitalia" 









