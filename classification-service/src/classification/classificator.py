from abc import ABC, abstractmethod
from ultralytics import YOLO


class Classificator(ABC):

    @abstractmethod
    def classify(self, image):
        pass


class YoloV8Classificator(Classificator):

    def __init__(self):
        self.model = YOLO('yolov8n-cls.pt')

    def classify(self, image):
        prediction = self.model.predict(image)
        return prediction[0].probs.top5[5]