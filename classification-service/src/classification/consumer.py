from aiokafka import AIOKafkaConsumer

CLASSIFICATION_TOPIC = 'image_classification'
BOOTSTRAP_SERVERS = 'localhost:92092'


class ImageClassificationConsumer:

    def __init__(self, loop):
        self.consumer = AIOKafkaConsumer(CLASSIFICATION_TOPIC, loop=loop, bootstrap_servers=BOOTSTRAP_SERVERS)

    async def consume(self):
        try:
            await self.consumer.start()
        except Exception as e:
            print(e)
            return

        try:
            async for message in self.consumer:
                print(message)
        finally:
            await self.consumer.stop()
