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

public val Icons.Mail: ImageVector
	get() {
		if (_Mail != null) {
			return _Mail!!
		}
		_Mail = ImageVector.Builder(
            name = "Mail",
            defaultWidth = 20.dp,
            defaultHeight = 21.dp,
            viewportWidth = 20f,
            viewportHeight = 21f
        ).apply {
			path(
    			fill = null,
    			fillAlpha = 1.0f,
    			stroke = SolidColor(Color(0xFF171A1F)),
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.71429f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 10f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(2.58142f, 4.47f)
				lineTo(9.99999f, 11.2143f)
				lineTo(17.4178f, 4.47072f)
			}
			path(
    			fill = null,
    			fillAlpha = 1.0f,
    			stroke = SolidColor(Color(0xFF171A1F)),
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.71429f,
    			strokeLineCap = StrokeCap.Square,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 10f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(16.4286f, 4.07143f)
				lineTo(3.57142f, 4.07143f)
				curveTo(2.78240f, 4.07140f, 2.14290f, 4.7110f, 2.14290f, 5.50f)
				lineTo(2.14285f, 15.5f)
				curveTo(2.14290f, 16.2890f, 2.78240f, 16.92860f, 3.57140f, 16.92860f)
				lineTo(16.4286f, 16.9286f)
				curveTo(17.21750f, 16.92860f, 17.85710f, 16.2890f, 17.85710f, 15.50f)
				lineTo(17.8571f, 5.5f)
				curveTo(17.85710f, 4.7110f, 17.21750f, 4.07140f, 16.42860f, 4.07140f)
				close()
			}
		}.build()
		return _Mail!!
	}

private var _Mail: ImageVector? = null
