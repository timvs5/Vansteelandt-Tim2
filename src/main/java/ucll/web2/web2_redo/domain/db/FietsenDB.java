package ucll.web2.web2_redo.domain.db;
// pas naam van de package aan naar je eigen situatie

import java.util.ArrayList;
import java.util.List;

import ucll.web2.web2_redo.domain.model.DomainException;
import ucll.web2.web2_redo.domain.model.Fiets;

public class FietsenDB {
    private int sequence = 1;
    private List<Fiets> fietsen = new ArrayList<>();

    public FietsenDB() {
        this.add(new Fiets("Giovanni", "Giant", 2018, 8300));
        this.add(new Fiets("Cedric", "Cube", 2015, 25500));
        this.add(new Fiets("Gerda", "Giant", 2020, 9578));
        this.add(new Fiets("Benny", "Bianchi", 2021, 1578));
        this.add(new Fiets("Patrick", "Pinarello", 2020, 17000));
        this.add(new Fiets("Bieke", "Bianchi", 2022, 1254));
    }

    public void add(Fiets fiets) {
        if (fiets == null)
            throw new DomainException("Geen geldige fiets om toe te voegen");
        fiets.setId(sequence++);
        fietsen.add(fiets);
    }

    public void addWithId(Fiets fiets) {
        if (fiets == null || fiets.getId() == 0)
            throw new DomainException("Geen geldige fiets om toe te voegen");
        fietsen.add(fiets);
    }

    public void remove(int ID) {
        for (int i = 0; i < this.fietsen.size(); i++) {
            if (this.fietsen.get(i).getId() == ID) {
                fietsen.remove(i);
            }
        }
    }

    public List<Fiets> getAlleFietsen() {
        return this.fietsen;
    }

    public Fiets getFietsMetMeesteKilometers() {
        if (fietsen.size() == 0)
            return null;
        Fiets fietsMetMeesteKilometers = fietsen.get(0);
        for (Fiets fiets : fietsen) {
            if (fiets.getKilometers() > fietsMetMeesteKilometers.getKilometers())
                fietsMetMeesteKilometers = fiets;
        }
        return fietsMetMeesteKilometers;
    }

    public void pasKilometersAan(int id, int kilometer) {
        getFietsById(id).setKilometers(kilometer);
    }

    public Fiets getFietsById(int id) {
        for (Fiets fiets : fietsen)
            if (fiets.getId() == id)
                return fiets;
        throw new DomainException("Er is geen fiets met gegeven id");
    }

    public List<Fiets> getFietsenVanMerk(String merk, ArrayList<String> errors) {
        try {
            return getFietsenVanMerk(merk);
        } catch (DomainException D) {
            errors.add(D.getMessage());
            return new ArrayList<>();
        }
    }


    public List<Fiets> getFietsenVanMerk(String merk) {
        if (merk == null || merk.trim().length() < 3)
            throw new DomainException("Het te zoeken merk moet minstens 3 karakters bevatten");
        List<Fiets> result = new ArrayList<>();
        for (Fiets fiets : getAlleFietsen()) {
            if (fiets.getMerk().toLowerCase().contains(merk.toLowerCase()))
                result.add(fiets);
        }
        if (result.size() > 0)
            return result;
        throw new DomainException("Geen fietsen gevonden van het merk " + merk);
    }

    public void verwijderFiets(int id) {
        fietsen.remove(getFietsById(id));
    }


    @Override
    public String toString() {
        String out = "";
        for (Fiets fiets : getAlleFietsen()
        ) {
            out += fiets.toString() + "\n";
        }
        return out;
    }
}
