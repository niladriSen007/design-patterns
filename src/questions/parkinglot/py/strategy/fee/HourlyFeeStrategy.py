from questions.py.entity import ParkingTicket
from questions.py.strategy.fee.FeeStrategy import FeeStrategy


class HourlyFeeStrategy(FeeStrategy):
    def __init__(self, hourly_rate: float):
        self._hourly_rate = hourly_rate

    def calculate_fee(self, ticket: ParkingTicket) -> float:
        return ticket.get_duration_in_hours() * self._hourly_rate
