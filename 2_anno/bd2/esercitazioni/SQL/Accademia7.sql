/* Esercitazione Accademia7 su database Accademia

*/

/*
1. Qual è media e deviazione standard degli 
  stipendi per ogni categoria di strutturati?

R :
"Professore Associato"	38211.143798828125	4359.258153186441
"Ricercatore"         	40304.271205357145	3602.198235119911
"Professore Ordinario"	39848.667317708336	2894.855495562445
*/

select p.posizione,
	       avg(p.stipendio) as media_stipendi,
		   stddev_samp(p.stipendio) as deviazione_standard
	from persona as p
	group by p.posizione 



/*
2. Quali sono i ricercatori (tutti gli attributi) con uno stipendio superiore alla media
della loro categoria?
R: 
0  "Anna"	"Bianchi"	    "Ricercatore"	      45500.3
2  "Barbara"	"Burso"	            "Ricercatore"	      40442.5
12 "Dario"   	"Basile"	    "Ricercatore"             42566
20 "Carla"	"Martinelli"	    "Ricercatore"	      42030.2

*/

select id, nome, cognome, posizione, stipendio
from persona, (
	select avg(persona.stipendio) as media_stip_ricercatore
	from persona 
	where persona.posizione = 'Ricercatore'
    ) as p2
where persona.posizione = 'Ricercatore' and
      persona.stipendio > p2.media_stip_ricercatore

/* 
  3. Per ogni categoria di strutturati quante sono le persone con uno stipendio che
  R:
  "Ricercatore"	          4
  "Professore Associato"	6
  "Professore Ordinario"	4
*/

select persona.posizione, count(*)
from persona,  
		(select persona.posizione, 
		       avg(persona.stipendio) as media_stip_cat,
      		   stddev_samp(persona.stipendio) as devs_stip_cat
		 from persona 
		 group by persona.posizione
		)as q1
where persona.posizione = q1.posizione and
      persona.stipendio <= (q1.media_stip_cat + q1.devs_stip_cat) and
	  persona.stipendio >= (q1.media_stip_cat - q1.devs_stip_cat) 

group by persona.posizione

/* 4. Chi sono gli strutturati che hanno lavorato 
   almeno 20 ore complessive in attività progettuali?
   Restituire tutti i loro dati e il numero di ore lavorate.
  R : 0	"Anna"	"Bianchi"	"Ricercatore"	45500.3	38
*/

select q1.*
from 
  ( select p.*, sum(ap.oredurata) as tot_ore_lav 
    from persona p, attivitaprogetto as ap  
    where ap.persona = p.id
    group by p.id
  )as q1 
  
  where  q1.tot_ore_lav >= 20

--oppure soluzione professore
select p.*, sum(ap.oreDurata) as ore_lavorate
from Persona p, AttivitaProgetto ap
where ap.persona = p.id
group by p.id, p.nome, p.cognome, p.posizione, p.stipendio
having sum(ap.oreDurata) >= 20;

/*
5. Quali sono i progetti la cui durata è superiore alla media delle durate di tutti i
progetti? Restituire nome dei progetti e loro durata in giorni.

2	"WineSharing"	"1999-01-01"	"2003-12-31"	998000	1825
3	"Simap"   	"2010-02-01"    "2014-03-17"    158000	1505
*/

with media_ore as ( 
	select avg(progetto.fine - progetto.inizio) as media
	from progetto
  )
select p.*, (p.fine -p.inizio) as durata_giorni
from progetto as p, media_ore
where (p.fine - p.inizio) > media_ore.media

/*
 6. Quali sono i progetti terminati in data odierna che hanno avuto attività di tipo
 “Dimostrazione”? Restituire nome di ogni progetto e il numero complessivo delle
 ore dedicate a tali attività nel progetto.

R: "Pegasus"	15	
*/

select pt.nome, sum(atp.oredurata) as ore_tot
from progetto as pt, attivitaprogetto as atp
where atp.progetto = pt.id and
      atp.tipo = 'Dimostrazione' and
	  pt.fine <= current_date
group by pt.id
/*
7. Quali sono i professori ordinari che hanno fatto più assenze per malattia 
   del numero di assenze medio per malattia dei professori associati? Restituire id, nome e
   cognome del professore e il numero di giorni di assenza per malattia.
R :  10	"Ginevra"	"Riva"	3
*/

select po.id, 
       po.nome, 
       po.cognome, 
      count(ao.id) as numero_assenze_malattia
	   
from persona as po, -- professori ordionarii
     assenza as ao, -- assenze professori ordinarii
     (    -- numero assenze per malattia per singolo professore associato
	  select count(aa.id) as numero_assenze  
	  from persona as pa, -- professori associati
	       assenza as aa  -- assenze professori associati
	  where pa.posizione = 'Professore Associato' and 
	        aa.persona = pa.id and
			aa.tipo = 'Malattia'
	  group by pa.id
	 
	 ) as n_associato_malattia
	 
 where po.posizione = 'Professore Ordinario' and
       ao.persona = po.id and
	   ao.tipo = 'Malattia'
 group by po.id, po.nome, po.cognome
 having count(ao.id) > avg(n_associato_malattia.numero_assenze) --media sul nuemero di assenze per malattia per singolo professore associato

 
 
