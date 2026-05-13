from questions.py.entity import ParkingTicket
from questions.py.strategy.fee.FeeStrategy import FeeStrategy


class FlatRateFeeStrategy(FeeStrategy):
    def __init__(self, flat_rate: float):
        self._flat_rate = flat_rate

    def calculate_fee(self, ticket: ParkingTicket) -> float:
        return self._flat_rate
