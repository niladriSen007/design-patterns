package behavioural.command;

class Light {
    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
}

class Thermostat {
    public void setTemperature(int temp) {
        System.out.println("Thermostat set to " + temp + "C");
    }
}

class SmartControllerNaive {
    private final Light light;
    private final ThermostatII thermostat;

    public SmartControllerNaive(Light light, ThermostatII thermostat) {
        this.light = light;
        this.thermostat = thermostat;
    }

    public void turnOnLight() {
        light.on();
    }

    public void turnOffLight() {
        light.off();
    }

    public void setThermostatTemperature(int temperature) {
        thermostat.setTemperature(temperature);
    }
}

public class WhyCommand {
    static void main() {
        Light light = new Light();
        ThermostatII thermostat = new ThermostatII();
        SmartControllerNaive controller = new SmartControllerNaive(light, thermostat);

        controller.turnOnLight();
        controller.setThermostatTemperature(22);
        controller.turnOffLight();
    }
}
