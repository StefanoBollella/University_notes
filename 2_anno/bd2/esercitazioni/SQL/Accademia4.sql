--Esercitazione su database Accademia

/*
  1. Quali sono i cognomi distinti di tutti gli strutturati?
  persona(id*, nome, cognome, posizione, stiopendio)
*/

 select distinct persona.cognome 
 from persona 
 where persona.posizione = 'Ricercatore' or
       persona.posizione = 'Professore Associato' or 
       persona.posizione = 'Professore Ordinario',

/*
  2.Quali sono i Ricercatori (con nome e cognome)?
  persona(id*, nome, cognome, posizione, stiopendio)
*/

select persona.nome, persona.cognome 
from persona 
where persona.posizione = 'Ricercatore'


/*
   3.Quali sono i Professori Associati il cui cognome comincia con la lettera ‘V’?
   persona(id*, nome, cognome, posizione, stiopendio)
*/

select * from persona 
where persona.posizione = 'Professore Associato' and persona.cognome like 'V%'


/*
   4. Quali sono i Professori (sia Associati che Ordinari) il cui cognome comincia con la lettera ‘V’?
   persona(id*, nome, cognome, posizione, stiopendio)
*/

select p1.nome,p1.cognome
from persona as p1, persona as p2
where p1.nome = p2.nome and p1.cognome = p2.cognome 
      and p1.cognome like 'V%' and
      p1.posizione != 'Ricercatore' and p2.posizione != 'Ricercatore'

-- oppure 

select id, nome, cognome from
persona where (persona.posizione = 'Professore Ordinario'
 or  persona.posizione = 'Professore Associato')and persona.cognome like 'V%'

/*
   5.Quali sono i Progetti già terminati alla data odierna?
   progetto(id,nome,inizio,fine,budget)
*/

select * from progetto
where progetto.fine <= CURRENT_DATE


/*
   6. Quali sono i nomi di tutti i Progetti ordinati in ordine crescente di data di inizio?
   progetto(id,nome,inizio,fine,budget)
*/

select progetto.nome
from progetto
order by progetto.inizio asc

/*
7. Quali sono i nomi dei WP ordinati in ordine crescente (per nome)?
WP (progetto: PosInteger, id: PosInteger, nome: StringaM, inizio: date, fine: date)
*/


select wp.nome
from wp
order by wp.nome asc

/*

 8. Quali sono (distinte) le cause di assenza di tutti gli strutturati?
 Assenza (id: PosInteger, persona: PosInteger, tipo: CausaAssenza, giorno: date)
 Persona (id: PosInteger, nome: StringaM, cognome: StringaM, posizione: Strutturato, stipendio: Denaro)
*/


Select distinct assenza.tipo 
from persona, assenza 
where persona.id = assenza.persona

/*
   9. Quali sono (distinte) le tipologie di attività di progetto di tutti gli strutturati?
   persona(id*, nome, cognome, posizione, stiopendio)
   AttivitaProgetto (id*,persona,progetto,wp,giorno,tipo, oreDurata)
*/


Select distinct atp.tipo
from persona, attivitaprogetto as atp
where persona.id = atp.persona
order by atp.tipo asc

/*
 10. Quali sono i giorni distinti nei quali del personale ha effettuato attività non pro- gettuali di tipo ‘Didattica’? Dare il risultato in ordine crescente.
 persona(id*, nome, cognome, posizione, stiopendio)
 AttivitaNonProgettuale (id* persona, tipo, giorno, oreDurata)

*/

select distinct atnp.giorno from persona, attivitanonprogettuale as atnp
where persona.id = atnp.persona and atnp.tipo = 'Didattica' 
order by atnp.giorno asc





