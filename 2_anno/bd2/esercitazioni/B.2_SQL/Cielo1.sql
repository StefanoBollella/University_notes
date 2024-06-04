/*
   11. Quali sono le compagnie che hanno voli che partono dall’aeroporto 
      ‘FCO’, atterrano all’aeroporto ‘JFK’ e di cui si conosce l’anno di fondazione?
*/
select c.nome 
from volo as v, arrpart as arp, compagnia as c
where arp.codice = v.codice
      and arp.comp = arp.comp
	  and arp.partenza = 'FCO'
	  and arp.arrivo = 'JFK'
	  and v.comp = c.nome 
	  and c.annofondaz is not null;


/*
   10. Quali sono i possibili piani di volo con  esattamente un cambio (utilizzando solo
       voli della stessa compagnia) da un qualunque aeroporto della città di ‘Roma’ ad un
       qualunque aeroporto della città di ‘New York’? Restituire: nome della compagnia, codici dei voli,
       e aeroporti di partenza, scalo e arrivo.
*/

select arp1.comp as compagnia,
       arp1.codice as volo1,
	   arp1.partenza as partenza,
	   arp1.arrivo as scalo,
	   arp2.codice as volo2,
	   arp2.arrivo as arrivo
	   
from arrpart as arp1,
	 luogoAeroporto as l1_p,
	 arrpart as arp2,
	 luogoAeroporto as l2_a

where l1_p.citta = 'Roma'
      and l2_a.citta = 'New York'
	  and arp1.partenza = l1_p.aeroporto
	  and arp2.arrivo = l2_a.aeroporto
	  and arp1.arrivo = arp2.partenza -- aeroporto arrivo lo stesso di partenza
	  and arp1.comp = arp2.comp --stessa compagnia
	--NO  and arp1.arrivo != arp2.arrivo -- aeroporto scalo diverso dall'arrivo
	
/*
   9. Quali sono i voli che partono da un qualunque aeroporto della città di ‘Roma’ e atterrano ad un
      qualunque aeroporto della città di ‘New York’? Restituire: codice del volo, nome della compagnia,
      e aeroporti di partenza e arrivo.
*/

select arp.codice as codice_volo, 
       arp.comp as compagnia,
	   arp.partenza as partenza,
	   arp.arrivo as arrivo
from  arrpart as arp,
      luogoaeroporto as lp,
	  luogoaeroporto as la
where lp.citta = 'Roma'
      and la.citta ='New York'
	  and arp.partenza = lp.aeroporto
	  and arp.arrivo = la.aeroporto

/*
   8. Quali sono gli aeroporti (con codice IATA, nome e luogo) nei quali partono voli 
      della compagnia di nome ‘MagicFly’?
*/

select aeroporto.codice,
       aeroporto.nome,
	   luogoaeroporto.citta
from volo, compagnia, arrpart, aeroporto, luogoaeroporto
where compagnia.nome = 'MagicFly'
      and volo.comp = compagnia.nome
	  and arrpart.codice = volo.codice
	  and arrpart.comp = volo.comp
	  and arrpart.partenza = aeroporto.codice
	  and aeroporto.codice = luogoaeroporto.aeroporto

/*
  7. Quali sono i nomi delle compagnie che hanno voli diretti dalla città di ‘Roma’ alla
     città di ‘New York’?
*/
  
select distinct v.comp as nome_compagnia
from volo as v,
     arrpart as arp,
	 luogoaeroporto as l1,
     luogoaeroporto as l2
where arp.codice = v.codice 
      and arp.comp =v.comp 
	  and arp.partenza = l1.aeroporto
	  and l1.citta = 'Roma'
	  and arp.arrivo = l2.aeroporto
	  and l2.citta = 'New York'
/*	  
   6. Quali sono le compagnie che hanno voli che partono dall’aeroporto ‘FCO’ e atterrano
      all’aeroporto ‘JFK’?
*/
select arrpart.comp
from arrpart
where arrpart.partenza = 'FCO' and
      arrpart.arrivo = 'JFK'

/*
    5. Quali sono i voli (codice e nome della compagnia) 
       che partono dall’aeroporto ‘FCO’  e arrivano all’aeroporto ‘JFK’?
*/
 select arrpart.codice as codice_volo,
        arrpart.comp as nome_compagnia
 from arrpart
 where arrpart.partenza = 'FCO'
       and arrpart.arrivo = 'JFK'


/*
   4. Quali sono le compagnie che hanno voli che arrivano all’aeroporto con codice ‘FCO’?
*/
select distinct a.comp as compagnia
from arrpart as a
where a.partenza = 'FCO'

/*
   3. Quali sono i voli (codice e nome della compagnia) che partono dall’aeroporto con
      codice ‘CIA’?
*/

select volo.codice as codice_volo,
       volo.comp as compagnia_volo
from volo, arrpart, aeroporto
where volo.codice = arrpart.codice
      and volo.comp = arrpart.comp
	  and arrpart.partenza = aeroporto.codice
	  and aeroporto.codice = 'CIA'


-- 2. Quali sono le compagnie che hanno voli che superano le 3 ore?
select distinct c.nome as nome_compagnia, 
       c.annofondaz as anno_fondazione
from  volo as v, compagnia as c
where v.durataminuti > '180'
      and v.comp = c.nome

-- 1. Quali sono i voli (codice e nome della compagnia) la cui durata supera le 3 ore?

select v.codice as codice_volo,
       c.nome as nome_compagnia
from volo as v, compagnia as c
where v.durataminuti > '180'
      and v.comp = c.nome
	  
