/* Esercitazione su database Accademia
Persona (_id_, nome, cognome, posizione, stipendio)

Progetto (_id_, nome, inizio, fine, budget)

-  altra chiave: (nome) 
-  vincolo di ennupla: inizio < fine

WP (_progetto_, _id_, nome, inizio, fine)

- vincolo di ennupla: inizio < fine
- altra chiave: (progetto, nome)
- foreign key: progetto references Progetto(id)

AttivitaProgetto (_id_, persona, progetto, wp, giorno, tipo, oreDurata)
- foreign key: persona references Persona(id) 
- foreign key: (progetto, wp) references WP(progetto, id)

AttivitaNonProgettuale (_id_, persona, tipo, giorno, oreDurata)

- foreign key: persona references Persona(id)

Assenza (_id_, persona, tipo, giorno)

- altra chiave: (persona, giorno)
- foreign key: persona references Persona(id)

*/


/*
9.Quali sono il nome e il cognome degli strutturati che nello stesso giorno hanno sia
attività progettuali che attività non progettuali? Si richiede anche di proiettare il
giorno, il nome del progetto, il tipo di attività non progettuali e la durata in ore di
entrambe le attività.
*/
select distinct persona.nome as nome,
persona.cognome as cognome,
atp.giorno as giorno,
atnp.tipo as tipo,
atp.oredurata as "AP Durata",
atnp.oredurata as "ANP Durata"
                
from persona, attivitanonprogettuale as atnp, attivitaprogetto atp, progetto
where atnp.giorno = atp.giorno
      and atnp.persona = persona.id
      and atp.persona = persona.id
      and atp.progetto = progetto.id


/*
8.Quali sono il nome e il cognome degli strutturati che nello stesso giorno hanno 
sia attività progettuali che attività non progettuali?
*/

select distinct persona.id, persona.nome, persona.cognome
from persona, attivitanonprogettuale as atnp, attivitaprogetto atp
where atnp.giorno = atp.giorno
      and atnp.persona = persona.id
      and atp.persona = persona.id

/*
7. Quali sono il nome, il cognome e la posizione dei Ricercatori che
   hanno più di un impegno per didattica?
*/
select distinct persona.id, persona.nome, persona.cognome, persona.posizione
from persona, attivitanonprogettuale as atnp1,attivitanonprogettuale as atnp2
where atnp1.persona = persona.id 
      and atnp2.persona = persona.id
      and persona.posizione = 'Ricercatore'
	  and atnp1.tipo = 'Didattica'
	  and atnp2.tipo = atnp1.tipo
	  and atnp1.id <> atnp2.id

/*
5. Quali sono il nome, il cognome e la posizione dei Professori Ordinari che hanno
   fatto più di una assenza per malattia?
   Più di due assenze diverse per malattia ma fatte dalla stessa persona 
*/
select distinct p1.id, p1.nome, p1.cognome, p1.posizione
from persona as p1, assenza as a1, assenza as a2
where a1.persona = p1.id
      and a2.persona = p1.id
	  and a1.id <> a2.id
	  and a1.tipo = 'Malattia'
	  and a2.tipo = a1.tipo
      and p1.posizione = 'Professore Ordinario'
 
/*
4. Quali sono il nome, il cognome e la posizione dei Professori Ordinari che hanno
fatto almeno una assenza per malattia?
*/
select distinct persona.nome, persona.cognome, persona.posizione
from persona, assenza
where assenza.persona = persona.id
      and assenza.tipo = 'Malattia'
      and persona.posizione = 'Professore Ordinario'
      
/*
3. Quali sono il nome, il cognome e la posizione delle Persone che hanno più di
una attività nel progetto ‘Pegasus’?
*/
select distinct persona.nome, persona.cognome, persona.posizione
from attivitaprogetto as atp1, attivitaprogetto as atp2,  progetto, persona
where progetto.nome = 'Pegasus'
       and atp1.progetto = progetto.id
	   and atp1.persona = persona.id
       and atp2.progetto = progetto.id
	   and atp2.persona = persona.id
	   and atp1.id <> atp2.id 

/*
2. Quali sono il nome, il cognome e 
   la posizione degli strutturati 
   che hanno almeno una attività nel progetto 
   ‘Pegasus’, ordinati per cognome decrescente?
*/
select distinct persona.nome, persona.cognome, persona.posizione
from attivitaprogetto, progetto, persona
where progetto.nome = 'Pegasus'
       and attivitaprogetto.progetto = progetto.id
	   and attivitaprogetto.persona = persona.id
order by persona.cognome desc
 
	  
/*
1. Quali sono il nome, la data di inizio e 
   la data di fine dei WP del
   progetto di nome ‘Pegasus’?
 */ 
select progetto.nome, progetto.inizio, progetto.fine
from wp, progetto
where wp.progetto = progetto.id and progetto.nome = 'Pegasus'

