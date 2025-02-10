package com.amica.help;

import com.sun.source.tree.Tree;

import java.util.*;

import static com.amica.help.Clock.getTime;
import static com.amica.help.Clock.format;

public class helpDesk implements HelpDeskAPI{
    ArrayList<Ticket> ticketList = new ArrayList<>();
    ArrayList<Technician> technicianList = new ArrayList<>();
    SortedSet<Synonym> synonymList = new TreeSet<>();


    @Override
    public int createTicket(String originator, String description, Ticket.Priority priority) {
        //finds next available tech, creates a new ticket, adds note and assigns the tech
        Technician technician = getAvailableTechnician();
        int newTicketID = getLastTicketID() + 1;
        ticketList.add(new Ticket(newTicketID,originator,description,getTime(),priority));
        addNote (newTicketID,"Created ticket.");
        String techID = technician.getID();
        assignTechnician(newTicketID,techID);
        return newTicketID;
    }

    public int getLastTicketID() {
        //Return the size of Ticket ArrayList
        return ticketList.size();
    }

    public Technician getAvailableTechnician(){
        //Iterates through all technicians looking for the one that has the lowest assigned tickets. Req #5
        //Assume the first tech
        Technician availableTechnician = technicianList.get(0);
        long lowestTicketCount = technicianList.get(0).activeTicketCount;
        for (int i = 1; i < technicianList.size(); i++){
            if (technicianList.get(i).getActiveTicketCount() < lowestTicketCount) {
                availableTechnician = technicianList.get(i);
                lowestTicketCount = technicianList.get(i).getActiveTicketCount();
            }
        }
        return availableTechnician;
    }

    @Override
    public void createTechnician(String technicianID, String name, long extension) {
        Technician newTechnician = new Technician (technicianID, name, extension, 0,0);
        technicianList.add(newTechnician);
    }
    public Technician getTechnicianByID (String techID) {
        Technician tech = technicianList.get(0);
        for (Technician i: technicianList) {
            if (i.getID().equals(techID)) {
                tech = i;
                break;
            }
        }
        return tech;
    }
    public void assignTechnician (int ticketID, String techID){
        Ticket ticket = getTicketByID(ticketID);
        ticket.setTechnicianID(techID);
        //Set ticket to Assigned
        ticket.setStatus(Ticket.Status.ASSIGNED);
        //Increase technician's assigned ticket count
        Technician tech = getTechnicianByID(techID);
        tech.setActiveTicketCount(tech.getActiveTicketCount() + 1);
        //Add note to ticket
        addNote (ticketID,"Assigned to Technician " + tech.getName() + ", " + tech.getID());
    }

    public void addNote(int ticketID, String note) {
        Ticket ticket = getTicketByID(ticketID);
        Events event = new Events(ticketID,getTime(),note);
        //ticketList.get((int) (ticketID - 1)).eventsList.add (new Events (ticketID, getTime(), note));
        ticket.eventsList.add(event);
    }

    //@Override
    public void resolveTicket(int ticketID, String note) {
        Ticket ticket = getTicketByID(ticketID);
        Technician tech = getTechnicianByID(ticket.getTechnicianID());
        tech.setActiveTicketCount(tech.getActiveTicketCount() - 1);
        tech.setResolvedTicketCount(tech.getResolvedTicketCount() + 1);
        //Set status to resolved
        ticket.setStatus(Ticket.Status.RESOLVED);
        addNote (ticketID,"Status set to RESOLVED.");
        //Set resolution timestamp
        ticket.setResolved(getTime());
        //Calculate resolution time (minutes)
        long resolutionTime = (ticket.getResolved() - ticket.getCreated()) / 60000; //expressed in minutes
        ticket.setMinutesResolved(resolutionTime);
        //addNote (ticketID,"Assigned to Technician " + technician.getName() + ", " + technician.getID());
    }

    //@Override
    public void addTags(int ticketID, String... tags) {
        Ticket ticket = getTicketByID(ticketID);
        ticket.tagList.addAll(Arrays.asList(tags));
    }

    public String getTag(String tag) {
        //Returns the defined tag synonym??
        //int index = synonymList.indexOf(tag);
        //if (index == -1) return ""; //TODO: should I return null instead?
        //else return synonymList.get(synonymList.indexOf(tag)).getSynonym();
        return null;
    }

    public void addSynonym(String tag, String synonym) {
        synonymList.add(new Synonym(tag, synonym));
    }

    //@Override
    public int reopenTicket(int priorTicketID, String reason, Ticket.Priority priority) {
        //finds next available tech, creates a new ticket, adds note, assigns the tech, links to previous ticket
        Technician technician = getAvailableTechnician();
        int newTicketID = getLastTicketID() + 1;
        Ticket priorTicket = getTicketByID(priorTicketID);
        Ticket newTicket =new Ticket(newTicketID,priorTicket.getOriginator(),priorTicket.getDescription(),getTime(),priority);
        ticketList.add(newTicket);
        //Copies all notes from previous ticket (Req. #19)
        for (int i = 0; i < priorTicket.eventsList.size(); i++) {
            addNote (newTicketID, priorTicket.getEventsList().get(i).getNote());
        }
        addNote (newTicketID,"Ticket reopened from ticket #" + priorTicketID + ". Reason: " + reason);
        newTicket.setPriority(priority);
        assignTechnician(newTicketID,technician.getID());

        return newTicketID;
    }

    public Ticket getTicketByID(int ticketID) {
        if (ticketID > 0) {
            for (Ticket ticket : ticketList) {
                if (ticket.getID() == ticketID) {
                    return ticket;
                }
            }
        }
        return null;
    }

    @Override
    public SortedSet<Ticket> getTickets() {
        //TODO
        return null;
    }

    @Override
    public List<Ticket> getTicketsByStatus(Ticket.Status status) {
        ArrayList<Ticket> ticketListResult = new ArrayList<>();
        for (Ticket i: ticketList) {
            if (i.getStatus().equals(status)) {
                ticketListResult.add(i);
            }
        }
        return ticketListResult;
    }

    @Override
    public void listAllTickets() {
        for (Ticket i: ticketList) {
            System.out.println("Ticket  : " + i.getID());
            System.out.println("Status  : " + i.getStatus());
            System.out.println("Priority: " + i.getPriority());
            System.out.println("Created : " + format(i.getCreated()));
            System.out.println("Resolved: " + format(i.getResolved()) + " (" + i.getMinutesResolved() + " mins.)");
            System.out.println("Tech    : " + i.getTechnicianID());
            //Prints Notes
            for (int n = 0; n < i.eventsList.size(); n++) {
                System.out.println(format(i.getEventsList().get(n).getTimestamp()) + ": " + i.getEventsList().get(n).getNote());
            }
            System.out.println();;
        }
    }

    @Override
    public List<Ticket> getTicketsByNotStatus(Ticket.Status status) {
        ArrayList<Ticket> ticketListResult = new ArrayList<>();
        for (Ticket i: ticketList) {
            if (! i.getStatus().equals(status)) {
                ticketListResult.add(i);
            }
        }
        return ticketListResult;
    }

    @Override
    public List<Ticket> getTicketsByTechnician(String techID) {
        ArrayList<Ticket> ticketListResult = new ArrayList<>();
        for (Ticket i: ticketList) {
            if (i.getTechnicianID().equals(techID)) {
                ticketListResult.add(i);
            }
        }
        return ticketListResult;
    }

    @Override
    public List<Ticket> getTicketsWithAnyTag(String... tags) {
        //TODO
        return null;
    }

    @Override
    public int getAverageMinutesToResolve() {
        Map<String, Integer> hm = getAverageMinutesToResolvePerTechnician();
        int avgMinutes = 0;
        for (Map.Entry<String, Integer> set: hm.entrySet()) {
            avgMinutes = avgMinutes + set.getValue();
        }
        return avgMinutes / hm.size();
    }

    @Override
    public Map<String, Integer> getAverageMinutesToResolvePerTechnician() {
        Map<String, Integer> hm = new HashMap<>();
        //build map
        for (Technician tech: technicianList){
            if (tech.getResolvedTicketCount() > 0) {
                hm.put(tech.getID(), 0);
            }
        }
        //Sum all ticket resolve time (in minutes)
        for (Ticket ticket: ticketList) {
            //Sums all minutes resolved field for each technician
            if (ticket.getStatus() == Ticket.Status.RESOLVED) {
                hm.replace(ticket.getTechnicianID(), ticket.getMinutesResolved() + hm.get(ticket.getTechnicianID()));
            }
            }
        //Now calculates average
        //for (Technician tech: technicianList){
        for (Map.Entry<String, Integer> set: hm.entrySet()) {
            //Get tech's # of resolved tickets
            int ticketCount = 0;
            for (Technician tech: technicianList) {
                if (tech.getID() == set.getKey()) {
                    ticketCount = tech.getResolvedTicketCount();
                }
            }
            //Calculate avg time to resolve
            int sumTotalMinutesToResolve = hm.get(set.getKey());
            hm.replace(set.getKey(), sumTotalMinutesToResolve / ticketCount);
        }
        return hm;
    }

    @Override
    public List<Ticket> getTicketsByText(String text) {
        ArrayList<Ticket> ticketListResult = new ArrayList<>();
        for (Ticket t: ticketList) {
            boolean found = false;
            //Search in the ticket description
            if (t.getDescription().toLowerCase().contains(text.toLowerCase())) {
                found = true;
            } else {
                //Search in the notes
                //Events event = new Events(ticketID,getTime(),note);
                for (int i = 0; i < t.eventsList.size(); i++) {
                    if (t.eventsList.get(i).getNote().toLowerCase().contains(text.toLowerCase())) {
                        found = true;
                    }
                }
            }
            if (found) {
                ticketListResult.add(t);
            }
        }
        return ticketListResult;
    }
}
