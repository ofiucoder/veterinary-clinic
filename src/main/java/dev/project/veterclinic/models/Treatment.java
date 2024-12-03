package dev.project.veterclinic.models;

public class Treatment {
        private String name;
        private int duration;
        private double cost;
    
        public Treatment(String name, int duration, double cost) {
            this.name = name;
            this.duration = duration;
            this.cost = cost;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public int getDuration() {
            return duration;
        }
    
        public void setDuration(int duration) {
            this.duration = duration;
        }
    
        public double getCost() {
            return cost;
        }
    
        public void setCost(double cost) {
            this.cost = cost;
        }
    
        @Override
        public String toString() {
            return "Treatment: " + name + ", Duration: " + duration + " days, Cost: $" + cost;
        }
    }
    

