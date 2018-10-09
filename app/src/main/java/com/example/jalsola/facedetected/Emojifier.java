package com.example.jalsola.facedetected;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class Emojifier {
    private static final String LOG_TAG = Emojifier.class.getSimpleName();
    private static final double SMILING_PROB_THRESHOLD = .15;
    private static final double EYE_OPEN_PROB_THRESHOLD = .5;

    /**
     * Method for detecting faces in a bitmap.
     *
     * @param context The application context.
     * @param picture The picture in which to detect the faces.
     */

    static void detectFaces(Context context, Bitmap picture) {
        // Create the face detector, disable tracking and enable classifications

        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        // Build the frame
        Frame frame = new Frame.Builder().setBitmap(picture).build();

        // Detect the faces
        SparseArray<Face> faces = detector.detect(frame);
        // Log the number of faces
        Log.e(LOG_TAG, "detectFaces: number of faces = " + faces.size());
        // If there are no faces detected, show a Toast message
        if (faces.size() == 0) {
            Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
        } else {
            // Iterate through the faces
            for (int i = 0; i < faces.size(); i++) {
                Face face = faces.valueAt(i);
                // Log the classification probabilities for each face.
                getClassifications(face);
            }
        }
        // Release the detector
        detector.release();
    }

    /**
     * Method for logging the classification probabilities.
     *
     * @param face The face to get the classification probabilities.
     */
    private static void getClassifications(Face face) {
        // Log all the probabilities
        Log.d(LOG_TAG, "getClassifications: smilingProb = " + face.getIsSmilingProbability());
        Log.d(LOG_TAG, "getClassifications: leftEyeOpenProb = "
                + face.getIsLeftEyeOpenProbability());
        Log.d(LOG_TAG, "getClassifications: rightEyeOpenProb = "
                + face.getIsRightEyeOpenProbability());

    }


}