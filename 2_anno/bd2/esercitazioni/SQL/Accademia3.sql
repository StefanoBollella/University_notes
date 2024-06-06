--domini specializzati

create domain PosInteger as integer
default 0
check(value>=0);

create domain StringaM as varchar(100);

create domain Denaro as real
default 0
check(value >= 0);

create domain NumeroOne as integer
default 0
check(value >= 0 and value <= 8);

--domini tipo enumerativo 

create type Strutturato as 
enum('Ricercatore', 'Professore Associato', 'Professore Ordinario');

create type LavoroProgetto as
enum('Ricerca e Sviluppo', 'Dimostrazione','Management','Altro');

create type LavoroNonProgettuale as
enum('Didattica','Ricerca','Missione','Incontro Dipartimentale',
    'Incontro Accademico', 'Altro');

create type CausaAssenza as
enum('Chiusura Universitaria','Malattia');

--tabelle. 

create table Persona(
 id PosInteger,
 nome StringaM not null,
 cognome StringaM not null,
 posizione Strutturato not null,
 stipendio Denaro not null,
 primary key(id)
);
 
create table Progetto(
 id PosInteger, 
 nome StringaM not null,
 inizio date not null,
 fine date not null,
 budget Denaro not null,
 primary key(id),
 unique(nome),
 check(inizio < fine)
);
 
create table WP(
  progetto PosInteger,
  id PosInteger,
  nome StringaM not null,
  inizio date not null,
  fine date not null,
  check(inizio < fine),
  primary key(progetto,id),
  unique(progetto, nome),
  foreign key(progetto) references Progetto(id)
);
 
 create table AttivitaProgetto(
  id PosInteger,
  persona PosInteger not null,
  progetto PosInteger not null,
  wp PosInteger not null,
  giorno date not null,
  tipo LavoroProgetto not null,
  oreDurata NumeroOne not null,
  primary key(id),
  foreign key(persona) references Persona(id),
  foreign key(progetto,wp) references WP(progetto,id)
 );
 
create table AttivitaNonProgettuale(
  id PosInteger,
  persona PosInteger not null,
  tipo LavoroNonProgettuale not null,
  giorno date not null,
  oreDurata NumeroOne not null,
  primary key(id),
  foreign key(persona) references Persona(id)
);

create table Assenza(
  id PosInteger,
  persona PosInteger not null,
  tipo CausaAssenza not null,
  giorno date not null,
  primary key(id),
  unique(persona,giorno),
  foreign key(persona) references Persona(id)
);


