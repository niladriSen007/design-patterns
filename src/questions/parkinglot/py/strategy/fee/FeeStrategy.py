from abc import ABC, abstractmethod

from questions.py.entity import ParkingTicket


class FeeStrategy(ABC):
    @abstractmethod
    def calculate_fee(self, ticket: ParkingTicket) -> float:
        pass
