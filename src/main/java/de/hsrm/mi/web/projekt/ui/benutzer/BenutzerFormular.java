package de.hsrm.mi.web.projekt.ui.benutzer;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class BenutzerFormular {
    @Id
    @NotBlank
    private String benutzername;
    
    @NotBlank
    @Size(min = 2, max = 80)
    private String passwort;

    private String passwortwiederholung;

    private String rolle = "USER";
    @Min(0)
    private int punkte;

    public void toBenutzer(Benutzer b) {
        // befuellt b mit Formularinhalt
        b.setBenutzername(benutzername);
        b.setPasswort(passwort);
        b.setPunkte(punkte);
        b.setRolle(rolle);
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
    public String getPasswortwiederholung() {
        return passwortwiederholung;
    }
    public void setPasswortwiederholung(String passwortwiederholung) {
        this.passwortwiederholung = passwortwiederholung;
    }

}
