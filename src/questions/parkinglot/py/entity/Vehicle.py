from abc import ABC, abstractmethod
from questions.py.entity.VehicleSize import VehicleSize

class Vehicle(ABC):
    def __init__(self, license_plate: str, size: VehicleSize):
        if not license_plate or not license_plate.strip():
            raise ValueError("License plate cannot be empty.")
        self._license_plate = license_plate
        self._size = size

    @property
    def license_plate(self) -> str:
        """Returns the license plate of the vehicle."""
        return self._license_plate
    
    @property
    def size(self) -> VehicleSize:
        """Returns the size of the vehicle."""
        return self._size
    
    def __str__(self) -> str:
        return f"{self.__class__.__name__}[{self._license_plate}]"