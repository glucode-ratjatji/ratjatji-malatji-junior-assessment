package com.example.glucode_getitdone_to_do_list

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

private val WATER_COUNT_KEY = intPreferencesKey("water_count")

object TaskWidget : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(
        context: Context, id: GlanceId
    ) {
        provideContent {
            GlanceTheme {
                MyContent()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    private fun MyContent() {
        val prefs = currentState<Preferences>()
        val count = prefs[WATER_COUNT_KEY] ?: 0
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(14.dp)
                .background(GlanceTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Water tracker",
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            )

            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .background(GlanceTheme.colors.surface)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "-",
                    modifier = GlanceModifier
                        .clickable(actionRunCallback<DecrementWaterAction>())
                )

                Spacer(modifier = GlanceModifier.width(10.dp))

                Text(
                    text = "$count", modifier = GlanceModifier
                )

                Spacer(modifier = GlanceModifier.width(10.dp))

                Text(
                    text = "+",
                    modifier = GlanceModifier
                        .clickable(actionRunCallback<IncrementWaterAction>())
                )
            }
        }
    }
}


class IncrementWaterAction : ActionCallback {
    override suspend fun onAction(
        context: Context, glanceId: GlanceId, parameters: ActionParameters
    ) {
        updateAppWidgetState(
            context, PreferencesGlanceStateDefinition, glanceId
        ) { prefs ->
            prefs.toMutablePreferences().apply {
                this[WATER_COUNT_KEY] = (prefs[WATER_COUNT_KEY] ?: 0) + 1
            }
        }
        TaskWidget.update(context, glanceId)
    }
}

class DecrementWaterAction : ActionCallback {
    override suspend fun onAction(
        context: Context, glanceId: GlanceId, parameters: ActionParameters
    ) {
        updateAppWidgetState(
            context, PreferencesGlanceStateDefinition, glanceId
        ) { prefs ->
            prefs.toMutablePreferences().apply {

                val current = prefs[WATER_COUNT_KEY] ?: 0
                this[WATER_COUNT_KEY] = maxOf(0, current - 1)
            }
        }
        TaskWidget.update(context, glanceId)
    }
}
