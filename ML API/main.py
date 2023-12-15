import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import tensorflow as tf
from tensorflow import keras
import numpy as np
from PIL import Image
from flask import Flask, request, jsonify

model = keras.models.load_model("model.h5")

label = ['cup_cakes', 'fried_rice', 'pancakes','soup',
 'cheesecake', 'egg', 'salad', 'ice_cream', 'donuts',
 'hot_dog', 'bakso', 'sate', 'french_fries', 'waffles', 'spaghetti_bolognese',
 'rendang', 'pizza', 'hamburger','gado','churros']


app = Flask(__name__)



def predict_label(img):
    i = np.asarray(img) / 255.0
    i = np.reshape(np.newaxix, 224, 224, np.newaxis)
    predictions = model.predict(i)
    result = label[np.argmax(predictions)]
    return result


@app.route("/", method=["GET", "POST"])
def index():
    if request.method == "POST" :
        file = request.files.get('file')
        if file is None or file.filename == "":
            return jsonify({"error" : "No file"})

        try:
            image_bytes = file.read()
            img = Image.open(io.Bytes10(image_bytes))
            img = img.resize((224,224), image.NEARSET)
            predictions = predict_label(img)
            return predictions
            
        except Exception as e:
              return jsonify({"error" : str(e)})

    return "OK"

if __name__ == "__main__" :
    app.run(debug=True)
