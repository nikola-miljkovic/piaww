package org.nmiljkovic.models;
// Generated Aug 27, 2017 8:06:21 PM by Hibernate Tools 4.3.1



/**
 * FlightRadarsId generated by hbm2java
 */
public class FlightRadarsId  implements java.io.Serializable {


     private int position;
     private int flight;
     private String radar;

    public FlightRadarsId() {
    }

    public FlightRadarsId(int position, int flight, String radar) {
       this.position = position;
       this.flight = flight;
       this.radar = radar;
    }
   
    public int getPosition() {
        return this.position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    public int getFlight() {
        return this.flight;
    }
    
    public void setFlight(int flight) {
        this.flight = flight;
    }
    public String getRadar() {
        return this.radar;
    }
    
    public void setRadar(String radar) {
        this.radar = radar;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FlightRadarsId) ) return false;
		 FlightRadarsId castOther = ( FlightRadarsId ) other; 
         
		 return (this.getPosition()==castOther.getPosition())
 && (this.getFlight()==castOther.getFlight())
 && ( (this.getRadar()==castOther.getRadar()) || ( this.getRadar()!=null && castOther.getRadar()!=null && this.getRadar().equals(castOther.getRadar()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPosition();
         result = 37 * result + this.getFlight();
         result = 37 * result + ( getRadar() == null ? 0 : this.getRadar().hashCode() );
         return result;
   }   


}


