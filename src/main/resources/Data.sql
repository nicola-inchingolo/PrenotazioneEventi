insert into custom_user(nome,cognome,email,password,data_nascita,codice_fiscale, ruolo, attivo)
values
('Nicola','Inchingolo','nico.inchingolo03@gmail.com','Password!1','2003-08-05','NCHNCL03M05A662Z',3,true),
('Matteo','Convertino','matteo.convertino@gmail.com','Password!2','2003-09-23','CNVMTT03M23S661Z',2,true),
('Daniele','Cozzi','daniele.cozzi@gmail.com','Password!3','2003-03-18','CZZDNL03M18S663Z',1,true),
('Edoardo','Loconte','edoardo.loconte@gmail.com','Password!4','2003-04-17','EDRLCN03M17S664Z',0,true),
('Edo','Locoe','do.locote@gmail.com','Password!5','2003-04-17','EDRLCN03M17S664Z',0,false),
('kanguro','king','k.k@gmail.com','Password!6','2003-04-17','EDRLCN03M17S664Z',0,false);


insert into Luogo(nome,via,città,provincia,cap)
values
('Colosseo', 'via del Corso', 'ROMA', 'RM', 00159);

insert into Sezione(nome, id_luogo)
values
('A',1);

insert into Posto(nome,id_sezione)
values
('1', 1);

insert into Evento(nome,descrizione)
values ('Tomorrowland', 'boh');

insert into Ripetizione(datainizio, datafine, ora_inizio, ora_fine, id_evento, id_luogo)
values ('2023-12-12','2023-12-14','08:00','13:00',1, 1);

insert into Biglietto(prezzo, venduto, id_user, id_posto, id_ripetizione)
values (12, true, 4, 1, 1);

insert into Recensione(descrizione,votazione,id_user,id_evento)
values
    ('servizio del cinema scadente', 1,4, 1);