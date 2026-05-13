from questions.py.entity.VehicleSize import VehicleSize
from questions.py.entity.Vehicle import Vehicle
from questions.py.exceptions import ParkingException
from typing import Optional
import threading


class ParkingSpot:
    def __init__(self, spot_id: str, size: VehicleSize):
        self._spot_id = spot_id
        self._spot_size = size
        self._parked_vehicle: Optional[Vehicle] = None
        self._lock = threading.Lock()

    def is_spot_available(self) -> bool:
        return self._parked_vehicle is None

    def can_vehicle_fit(self, vehicle_size: VehicleSize):
        return self._spot_size >= vehicle_size

    def park_vehicle(self, vehicle: Vehicle):
        with self._lock:
            if not self.is_spot_available():
                raise ParkingException(f"Spot {self._spot_id} is already occupied")
            if not self.can_vehicle_fit(vehicle.size):
                raise ParkingException(
                    f"Vehicle size {vehicle.size.name} cannot fit in spot size {self._size.name}"
                )
            self._parked_vehicle = vehicle

    def unpark_vehicle(self):
        with self._lock:
            if self.is_spot_available():
                raise ParkingException(
                    f"Spot {self._spot_id} is already empty")
            vehicle = self._parked_vehicle
            self._parked_vehicle = None
            return vehicle

    @property
    def spot_id(self) -> str:
        return self._spot_id

    @property
    def size(self) -> VehicleSize:
        return self._size

    @property
    def parked_vehicle(self) -> Optional[Vehicle]:
        return self._parked_vehicle
