package com.example.glucode_getitdone_to_do_list.glance

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.example.glucode_getitdone_to_do_list.TaskWidget

class TaskReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TaskWidget
}