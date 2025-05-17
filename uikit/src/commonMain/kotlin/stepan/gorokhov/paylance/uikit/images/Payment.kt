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

public val Icons.Payment: ImageVector
	get() {
		if (_Payment != null) {
			return _Payment!!
		}
		_Payment = ImageVector.Builder(
            name = "Payment",
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
				moveTo(560f, 520f)
				quadToRelative(-50f, 0f, -85f, -35f)
				reflectiveQuadToRelative(-35f, -85f)
				quadToRelative(0f, -50f, 35f, -85f)
				reflectiveQuadToRelative(85f, -35f)
				quadToRelative(50f, 0f, 85f, 35f)
				reflectiveQuadToRelative(35f, 85f)
				quadToRelative(0f, 50f, -35f, 85f)
				reflectiveQuadToRelative(-85f, 35f)
				close()
				moveTo(280f, 640f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(200f, 560f)
				verticalLineToRelative(-320f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(280f, 160f)
				horizontalLineToRelative(560f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(920f, 240f)
				verticalLineToRelative(320f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(840f, 640f)
				horizontalLineTo(280f)
				close()
				moveToRelative(80f, -80f)
				horizontalLineToRelative(400f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(840f, 480f)
				verticalLineToRelative(-160f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(760f, 240f)
				horizontalLineTo(360f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(280f, 320f)
				verticalLineToRelative(160f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(360f, 560f)
				close()
				moveToRelative(440f, 240f)
				horizontalLineTo(120f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(40f, 720f)
				verticalLineToRelative(-440f)
				horizontalLineToRelative(80f)
				verticalLineToRelative(440f)
				horizontalLineToRelative(680f)
				verticalLineToRelative(80f)
				close()
				moveTo(280f, 560f)
				verticalLineToRelative(-320f)
				verticalLineToRelative(320f)
				close()
			}
		}.build()
		return _Payment!!
	}

private var _Payment: ImageVector? = null
