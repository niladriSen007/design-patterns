from typing import Dict,List
from questions.py.entity.VehicleSize import VehicleSize as VehicleSizeType
from questions.py.entity import VehicleSize
from questions.py.entity.ParkingSpot import ParkingSpot

class ParkingFloor:
    def __init__(self, floor_number: int, spot_counts : Dict[VehicleSizeType,int]):
        self._floor_number = floor_number
        self._spot_counts:List[ParkingSpot] = []
        
    def _initialize_spots(self, spot_counts: Dict[VehicleSize, int]) -> None :
        for vehicle_size in VehicleSize:
            count = spot_counts.get(vehicle_size,0)
            prefix = self._get_size_prefix(vehicle_size)
            for i in range(1,count+1):
                spot_id = f"F{self._floor_number}-{prefix}{i::03d}"
                self._spot_counts.append(ParkingSpot(spot_id,vehicle_size))

    def _get_size_prefix(self, vehicle_size: VehicleSizeType):
        prefix_mapping : Dict[VehicleSize,str] = {
            VehicleSize.SMALL : 'S',
            VehicleSize.MEDIUM : 'M',
            VehicleSize.LARGE : 'L'
        }
        return prefix_mapping.get(vehicle_size,"X")

    def
