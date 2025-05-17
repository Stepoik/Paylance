package stepan.gorokhov.paylance.uikit.images

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Icons.Work: ImageVector
	get() {
		if (_Work != null) {
			return _Work!!
		}
		_Work = ImageVector.Builder(
            name = "Work",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
			path(
    			fill = SolidColor(Color(0xFF1F1F1F)),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(160f, 840f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(80f, 760f)
				verticalLineToRelative(-440f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(160f, 240f)
				horizontalLineToRelative(160f)
				verticalLineToRelative(-80f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(400f, 80f)
				horizontalLineToRelative(160f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(640f, 160f)
				verticalLineToRelative(80f)
				horizontalLineToRelative(160f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(880f, 320f)
				verticalLineToRelative(440f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(800f, 840f)
				horizontalLineTo(160f)
				close()
				moveToRelative(0f, -80f)
				horizontalLineToRelative(640f)
				verticalLineToRelative(-440f)
				horizontalLineTo(160f)
				verticalLineToRelative(440f)
				close()
				moveToRelative(240f, -520f)
				horizontalLineToRelative(160f)
				verticalLineToRelative(-80f)
				horizontalLineTo(400f)
				verticalLineToRelative(80f)
				close()
				moveTo(160f, 760f)
				verticalLineToRelative(-440f)
				verticalLineToRelative(440f)
				close()
			}
		}.build()
		return _Work!!
	}

private var _Work: ImageVector? = null
