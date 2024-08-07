from kafka import KafkaConsumer
import json
import boto3
import pandas as pd
from ultralytics import YOLO

file_topic = 'files'
kafka_server_address = 'localhost:29092'

files_consumer = KafkaConsumer(file_topic, bootstrap_servers=[kafka_server_address], security_protocol='PLAINTEXT',
                               value_deserializer=lambda x: json.loads(x.decode('utf-8')))

test_key = 'test-key'
secret_key = 'secret-key'

s3 = boto3.client(
    service_name='s3',
    aws_access_key_id=test_key,
    aws_secret_access_key=secret_key,
    endpoint_url='http://localhost:4566',
)


def list_objs(bucket):
    response = s3.list_objects(Bucket=bucket)
    return pd.json_normalize(response['Contents'])


model = YOLO('yolov8n-cls.pt')


if __name__ == '__main__':
    while True:
        for message in files_consumer:
            print("Received message: {}".format(message.value))
            image_key = message.value['key']
            obj = s3.get_object(Bucket='files', Key=image_key)
            image = obj['Body'].read()

            file = open('./resources/images/{}'.format(image_key), 'wb')
            file.write(image)
            file.close()
            result = model.predict('./resources/images/{}'.format(image_key))
            # result = model(image)
            # print(result[0].boxes.data)
            top = result[0].probs.top5[0]
            # print(result[0].probs.cls)
            names = model.names
            print(names[top])
            # print(names)

