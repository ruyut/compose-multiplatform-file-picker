package com.darkrockstudios.libraries.mpfilepicker.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.darkrockstudios.libraries.mpfilepicker.DirectoryPicker
import com.darkrockstudios.libraries.mpfilepicker.FilePicker

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				Column {
					var showFilePicker by remember { mutableStateOf(false) }
					var pathChosen by remember { mutableStateOf("") }

					Button(onClick = {
						showFilePicker = true
					}) {
						Text("Choose File")
					}
					Text("File Chosen: $pathChosen")

					val fileType = listOf("jpg", "png")
					FilePicker(showFilePicker, fileExtensions = fileType) { mpFile ->
						if (mpFile != null) {
							pathChosen = mpFile.path
						}
						showFilePicker = false
					}

					/////////////////////////////////////////////////////////////////

					var showDirPicker by remember { mutableStateOf(false) }
					var dirChosen by remember { mutableStateOf("") }

					Button(onClick = {
						showDirPicker = true
					}) {
						Text("Choose Directory")
					}
					Text("Directory Chosen: $dirChosen")

					DirectoryPicker(showDirPicker) { path ->
						dirChosen = path ?: "none selected"
						showDirPicker = false
					}
				}
			}
		}
	}
}