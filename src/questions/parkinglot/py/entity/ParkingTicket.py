from questions.py.entity.Vehicle import Vehicle
from datetime import datetime
from typing import Optional


class ParkingTicket:
    def __init__(self, ticket_id: str, vehicle: Vehicle, spot: 'ParkingSpot'):
        self._ticket_id = ticket_id
        self._vehicle = vehicle
        self._spot = spot
        self._entry_time = datetime.now()
        self._exit_time: Optional[datetime] = None

    def set_exit_time(self, exit_time: datetime) -> None:
        """Sets the exit time for the parking ticket."""
        self._exit_time = exit_time

    def get_duration_in_hours(self) -> float:
        """Calculates the duration of parking in hours."""
        exit = self._exit_time if self._exit_time else datetime.now()
        duration = exit - self._entry_time
        hours = int(duration.total_seconds() // 3600)
        return max(1, hours)  # Minimum charge for 1 hour

    @property
    def ticket_id(self) -> str:
        return self._ticket_id

    @property
    def vehicle(self) -> Vehicle:
        return self._vehicle

    @property
    def spot(self) -> 'ParkingSpot':
        return self._spot

    @property
    def entry_time(self) -> datetime:
        return self._entry_time

    @property
    def exit_time(self) -> Optional[datetime]:
        return self._exit_time
