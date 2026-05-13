from questions.py.entity import Vehicle
from questions.py.entity.VehicleSize import VehicleSize

class Car(Vehicle):
    def __init__(self, license_plate: str):
        super().__init__(license_plate, VehicleSize.MEDIUM)