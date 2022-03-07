# BE-settimana-12-progetto

Progetto Settimana 12 - Gestione Catalogo Libri 

Per accedere: http://localhost:8080/swagger-ui.html

Per loggarsi come admin basterà segurie la procedura login con le seguenti credenziali:

username: admin
password: admin 

E' possibile anche loggarsi come user con le seguenti credenziali:

username: user 
password: user

Bisognerà poi utilizzare il token JWT che verrà generato per accedere tramite il pulsante authorize che si trova in alto a destra.

E' possibile creare un nuovi utente che saranno inseriti automaticamente nel database. Si possono inoltre creare utenti user e admin con credenziali personali.

I metodi GET possono essere utilizzati sia da user che da admin,

Mentre POST, PUT e DELETE solo da admin.

Ogni metodo è opportunamente commentato tramite documentazione api con le indicazioni da seguire per far funzionare le chiamate.

Presenti anche alcuni test base Junit ed il backup del database postgreSQL. Per fare il RESTORE ed importare correttamente il db bisognerà chiamarlo catalogo_libri.

POICHE' PER MOTIVI DI CONFIUGRAZIONE UTILIZZO LA PORTA 5433 IN postgreSQL POTREBBE ESSERE NECESSARIO CAMBIARLA NEL FILE APPLICATION.PROPERTIES IN 5432!!!
