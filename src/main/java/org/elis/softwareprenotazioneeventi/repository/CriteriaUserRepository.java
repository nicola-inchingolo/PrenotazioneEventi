package org.elis.softwareprenotazioneeventi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.elis.softwareprenotazioneeventi.DTO.request.FiltroAvanzato;
import org.elis.softwareprenotazioneeventi.DTO.request.FiltroRecensione;
import org.elis.softwareprenotazioneeventi.DTO.request.FiltroUser;
import org.elis.softwareprenotazioneeventi.model.Categoria;
import org.elis.softwareprenotazioneeventi.model.Evento;
import org.elis.softwareprenotazioneeventi.model.Recensione;
import org.elis.softwareprenotazioneeventi.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaUserRepository {
    private  final EntityManager manager;

    public CriteriaUserRepository(EntityManager e)
    {
        manager= e;
    }

    public List<User> getUserFiltered(FiltroUser request)
    {
        //creazione costruttore criteria Query
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        //creo la mia criteria query, specificandone il tipo  di ritorno
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        //creo l'oggetto che mi permettera di accedere ai "nomi" dei campi della classe User
        Root<User> root = cq.from(User.class);
        // creo la lista di predicate, utile ad applicare i filtri
        List<Predicate> predicate = new ArrayList<>(); // attualmente vuota

        //creo i vari filtri da applicare
        if(request.getNome()!=null && !request.getNome().isEmpty())
        {
            //creazione filtro su nome della request
            //in questo caso ignoro il case
            Predicate filtroNome = cb.like(cb.upper(root.get("nome")), "%" + request.getNome().toUpperCase() + "%");
            predicate.add(filtroNome);
        }
        if(request.getCognome()!=null && !request.getCognome().isEmpty())
        {
            Predicate filtroCognome = cb.like(root.get("cognome"), "%" + request.getCognome() + "%" );
            predicate.add(filtroCognome);
        }
        if(request.getEmail()!=null && !request.getEmail().isEmpty())
        {
            Predicate filtroEmail = cb.like(root.get("email"), "%" + request.getEmail() + "%" );
            predicate.add(filtroEmail);
        }
        if(request.getCodiceFiscale()!=null && !request.getCodiceFiscale().isEmpty())
        {
            Predicate filtroCodiceFiscale = cb.like(root.get("codiceFiscale"), request.getCodiceFiscale() );
            predicate.add(filtroCodiceFiscale);
        }
        // una volta creati i filtri, devo convertirli la lista in array per poterla settare alla criteriaQuery
        Predicate[] arrayDiPredicate = predicate.toArray(new Predicate[predicate.size()]);
        //setto i filtri della criteria query
        cq.where(arrayDiPredicate);
        //creo la query finale che sarà una query tipizzata
        TypedQuery<User> tq = manager.createQuery(cq);
        //prendo la lista dei risultati
        List<User> users = tq.getResultList();
        //ritorno i risultati
        return users;
    }

    public List<Evento> getEventiFiltrati(FiltroAvanzato request){

        //creo il costruttore d
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = builder.createQuery(Tuple.class);
        //dalla citeria query prendo la classe principale sulla quale applicare la query
        Root<Evento> root = cq.from(Evento.class);
        //sfruttando la JOIN vado a prendere l'altra classe appunto collegata con evento (categoria) attraverso un associazione qualunque
        Join<Evento, Categoria> join = root.join("categorie"); //lo faccio attraverso l'attributo che inidica l'associazione nella classe principale
        //creo la lista di predicate per applicare i filtri
        List<Predicate> predicate = new ArrayList<>();
        //faccio scorrere le chiavi della map filtroEvento, le chiavi saranno i nomi
        //dei campi presi in considerazione per il nostro filtro

        if(request.getFiltroEvento()!=null && !request.getFiltroEvento().isEmpty()) {
            for (String nomeCampo : request.getFiltroEvento().keySet()) {

                //prendo il valore che devo settare nel filtro di quel determinato campo
                String valoreCampo = request.getFiltroEvento().get(nomeCampo);
                Predicate p = builder.like(root.get(nomeCampo), "%" + valoreCampo + "%");
                predicate.add(p);
            }
        }

        if(request.getFiltroCategoria()!=null && !request.getFiltroCategoria().isEmpty()) {
            //adesso filtro la categoria
            for (String s : request.getFiltroCategoria().keySet()) {
                Predicate p = builder.like(join.get(s), "%" + request.getFiltroCategoria().get(s) + "%");
                predicate.add(p);
            }
        }

        //creati i predicati andrò a dire alla criteria query che la selezione non
        //verrà fatta solo su una tabella
        //ma su quali tabelle farla
        cq.multiselect(root,join).where(predicate.toArray(new Predicate[predicate.size()]));
        //quando eseguo la mia query vado a prendere non una lista di Eventi ma una lista di
        //tuple che avrà per ogni colonna un valore divero messi in ordine di come li ho passati
        //al metodo multiselect (colonna 0 ->root->Evento, Colonna 1 ->join->Categoria)
        List<Tuple> tuple = manager.createQuery(cq).getResultList();
        //tramite uno stream vado a riprendermi tutti gli elementi nella colonna che mi interessa
        //(mi interessano gli eventi quindi colonna 0), il metodo get della classe Tuple
        //torna un generics, quindi vuole sapere il tipo a cui Castare il nostro oggetto
        //Evento.class
        return tuple.stream().map(t->t.get(0, Evento.class)).toList();
    }

    public List<Recensione> getRecensioniFiltrate(FiltroRecensione request)
    {

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Recensione> root = cq.from(Recensione.class);
        Join<Recensione, User> joinuser = root.join("user");
        Join<Recensione, Evento> joinevento = root.join("evento");
        List<Predicate> predicate = new ArrayList<>();
        System.out.println(request);
        if(request.getDescrizione()!=null && !request.getDescrizione().isEmpty())
        {
            Predicate filtroDescrizione = cb.like(root.get("descrizione"), "%" + request.getDescrizione()+ "%");
            predicate.add(filtroDescrizione);
        }
        if(request.getVotazione()!=null && !request.getVotazione().isEmpty())
        {
            Predicate filtroVotazione = cb.greaterThanOrEqualTo(root.get("votazione"),   Integer.parseInt(request.getVotazione()));
            predicate.add(filtroVotazione);
        }
        if(request.getNomeUser()!=null && !request.getNomeUser().isEmpty())
        {
            Predicate filtroAutore = cb.like(joinuser.get("email"),"%" + request.getNomeUser() + "%");
            predicate.add(filtroAutore);
        }
        if(request.getNomeEvento()!=null && !request.getNomeEvento().isEmpty())
        {
            Predicate filtroEvento = cb.like(joinevento.get("nome"),"%" + request.getNomeEvento() + "%");
            predicate.add(filtroEvento);
        }
        System.out.println(predicate.size());
        cq.multiselect(root,joinuser,joinevento).where(predicate.toArray(new Predicate[predicate.size()]));

        List<Tuple> tuple = manager.createQuery(cq).getResultList();

        return tuple.stream().map(t->t.get(0, Recensione.class)).toList();
    }

    //Filtro per Recensione

    //Filtro per Ripeetizioni Evento da ora a ora  del giorno tot

    //Filtro Biglietto per costo?



}
