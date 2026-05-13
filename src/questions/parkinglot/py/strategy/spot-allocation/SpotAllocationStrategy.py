from abc import ABC, abstractmethod
from questions.py.entity.VehicleSize import VehicleSize


class SpotAllocationStrategy(ABC):
    @abstractmethod
    def allocate_spot(self, vehicle_size: VehicleSize):
        """ TODO """
        pass
