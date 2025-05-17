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

public val Icons.PhotoCamera: ImageVector
	get() {
		if (_PhotoCamera != null) {
			return _PhotoCamera!!
		}
		_PhotoCamera = ImageVector.Builder(
            name = "PhotoCamera",
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
				moveTo(480f, 700f)
				quadToRelative(75f, 0f, 127.5f, -52.5f)
				reflectiveQuadTo(660f, 520f)
				quadToRelative(0f, -75f, -52.5f, -127.5f)
				reflectiveQuadTo(480f, 340f)
				quadToRelative(-75f, 0f, -127.5f, 52.5f)
				reflectiveQuadTo(300f, 520f)
				quadToRelative(0f, 75f, 52.5f, 127.5f)
				reflectiveQuadTo(480f, 700f)
				close()
				moveToRelative(0f, -80f)
				quadToRelative(-42f, 0f, -71f, -29f)
				reflectiveQuadToRelative(-29f, -71f)
				quadToRelative(0f, -42f, 29f, -71f)
				reflectiveQuadToRelative(71f, -29f)
				quadToRelative(42f, 0f, 71f, 29f)
				reflectiveQuadToRelative(29f, 71f)
				quadToRelative(0f, 42f, -29f, 71f)
				reflectiveQuadToRelative(-71f, 29f)
				close()
				moveTo(160f, 840f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(80f, 760f)
				verticalLineToRelative(-480f)
				quadToRelative(0f, -33f, 23.5f, -56.5f)
				reflectiveQuadTo(160f, 200f)
				horizontalLineToRelative(126f)
				lineToRelative(74f, -80f)
				horizontalLineToRelative(240f)
				lineToRelative(74f, 80f)
				horizontalLineToRelative(126f)
				quadToRelative(33f, 0f, 56.5f, 23.5f)
				reflectiveQuadTo(880f, 280f)
				verticalLineToRelative(480f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(800f, 840f)
				horizontalLineTo(160f)
				close()
				moveToRelative(0f, -80f)
				horizontalLineToRelative(640f)
				verticalLineToRelative(-480f)
				horizontalLineTo(638f)
				lineToRelative(-73f, -80f)
				horizontalLineTo(395f)
				lineToRelative(-73f, 80f)
				horizontalLineTo(160f)
				verticalLineToRelative(480f)
				close()
				moveToRelative(320f, -240f)
				close()
			}
		}.build()
		return _PhotoCamera!!
	}

private var _PhotoCamera: ImageVector? = null
