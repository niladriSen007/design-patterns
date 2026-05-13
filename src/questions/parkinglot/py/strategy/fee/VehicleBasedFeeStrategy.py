from questions.py.entity import ParkingTicket
from questions.py.entity.VehicleSize import VehicleSize
from questions.py.strategy.fee.FeeStrategy import FeeStrategy


class VehicleBasedFeeStrategy(FeeStrategy):
    def __init__(self, rate_per_vehicle: dict[VehicleSize, float]):
        self._rate_per_vehicle = rate_per_vehicle

    def calculate_fee(self, ticket: ParkingTicket) -> float:
        vehicle_size = ticket.vehicle.vehicle_size
        rate = self._rate_per_vehicle.get(vehicle_size, 0.0)
        return ticket.get_duration_in_hours() * rate
