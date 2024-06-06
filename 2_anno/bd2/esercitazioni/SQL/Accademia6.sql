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

-- 1. Quanti sono gli strutturati di ogni fascia?

select persona.posizione, count(persona.posizione) as numero
from persona
group by(persona.posizione);

-- 2.Quanti sono gli strutturati con stipendio ≥ 40000?

 select count(persona.stipendio) as n_stip_maggiore_40k
 from persona
 where persona.stipendio >= 40000

-- 3. Quanti sono i progetti già finiti che superano il budget di 50000?

select count(progetto.id) as numero_progetti
from progetto
where progetto.fine < current_date and progetto.budget > 50000

-- 4. Qual è la media, il massimo e il minimo delle ore delle attività relative al progetto ‘Pegasus’? 

select avg(atp.oredurata) as media, min(atp.oredurata) as minumo, 
       max(atp.oredurata) as massimo
from progetto as p, attivitaprogetto as atp
where p.nome = 'Pegasus'
      and atp.progetto = p.id

/*
 5. Quali sono le medie, i massimi e i minimi delle ore giornaliere dedicate al progetto
    ‘Pegasus’ da ogni singolo docente?
*/

select doc.id as id_docente, 
       doc.nome, 
       doc.cognome,
       doc.posizione,
	   avg(atp.oredurata) as media_ore,
       min(atp.oredurata) as minimo, 
       max(atp.oredurata) as massimo	   
from persona as doc, progetto as p, attivitaprogetto as atp
where p.nome = 'Pegasus'
      and atp.persona = doc.id
	  and atp.progetto = p.id
group by(doc.id);

-- 6. Qual è il numero totale di ore dedicate alla didattica da ogni docente?

select persona.id,
       persona.nome,
       persona.cognome, 
       persona.posizione, 
       sum(atnp.oredurata) as ore_tot
from persona, attivitanonprogettuale as atnp
where atnp.tipo = 'Didattica'
      and atnp.persona = persona.id
group by(persona.id);


-- 7. Qual è la media, il massimo e il minimo degli stipendi dei ricercatori?
 
select avg(persona.stipendio) as media, 
       max(persona.stipendio) as massimo,
	   min(persona.stipendio) as minimo
from persona 
where persona.posizione = 'Ricercatore'


/*
  8. Quali sono le medie, i massimi e i minimi degli stipendi dei ricercatori, dei professori
  associati e dei professori ordinari?
*/

select persona.posizione,
       avg(persona.stipendio) as media, 
       max(persona.stipendio) as massimo,
	   min(persona.stipendio) as minimo
from persona
group by(persona.posizione)


-- 9. Quante ore ‘Ginevra Riva’ ha dedicato ad ogni progetto nel quale ha lavorato?

select progetto.id, 
       progetto.nome,
       sum(atp.oredurata) as ore_complessive
from persona, attivitaprogetto as atp, progetto
where persona.nome = 'Ginevra' 
      and persona.cognome = 'Riva'
	  and atp.persona = persona.id
	  and atp.progetto = progetto.id
group by (progetto.id, progetto.nome)


--10. Qual è il nome dei progetti su cui lavorano più  di due strutturati?

select pro.id, 
       pro.nome
from attivitaprogetto as atp, persona as p, progetto as pro
where atp.persona = p.id
       and atp.progetto = pro.id
group by (pro.id, pro.nome)
having count(distinct p.id) > 2

